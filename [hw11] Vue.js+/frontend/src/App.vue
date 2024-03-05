<template>
    <div id="app">
        <Header :user="user"/>
        <Middle :posts="posts" :users="users"/>
        <Footer/>
    </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
    name: 'App',
    components: {
        Footer,
        Middle,
        Header
    },
    data: function () {
        return {
            user: null,
            posts: [],
            users: []
        }
    },
    methods: {
        updatePosts() {
            axios.get("/api/1/posts").then(response => {
              this.posts = response.data;
            });
        },
        updateUsers() {
            axios.get("/api/1/users").then(response => {
                this.users = response.data;
            });
        }
    },
    beforeMount() {
        if (localStorage.getItem("jwt") && !this.user) {
            this.$root.$emit("onJwt", localStorage.getItem("jwt"));
        }

        this.updatePosts();
        this.updateUsers();
    },
    beforeCreate() {
        this.$root.$on("onEnter", (login, password) => {
            if (password === "") {
                this.$root.$emit("onEnterValidationError", "Password is required");
                return;
            }

            axios.post("/api/1/jwt", {
                    login, password
            }).then(response => {
                this.$root.$emit("onEnterImpl", response.data);
            }).catch(error => {
                this.$root.$emit("onEnterValidationError", error.response.data);
            });
        });

        this.$root.$on("onRegister", (login, name, password) => {
            if (login === "" || login === null) {
              this.$root.$emit("onRegisterValidationError", "Login can't be empty");
              return;
            }

            if (name === null || name.trim() === "") {
              this.$root.$emit("onRegisterValidationError", "Name can't be empty");
              return;
            }

            if (password === "" || password === null) {
                  this.$root.$emit("onRegisterValidationError", "Password can't be empty");
                  return;
            }

            axios.post("/api/1/users", {
              login, name, password
            }).then(response => {
                this.updateUsers();
                this.$root.$emit("onEnterImpl", response.data);
            }).catch(error => {
                this.$root.$emit("onRegisterValidationError", error.response.data);
            });
        });

        this.$root.$on("onEnterImpl", (data) => {
            localStorage.setItem("jwt", data);
            this.$root.$emit("onJwt", data);
        });

        this.$root.$on("onJwt", (jwt) => {
            localStorage.setItem("jwt", jwt);

            axios.get("/api/1/users/auth", {
                params: {
                    jwt
                }
            }).then(response => {
                this.user = response.data;
                this.$root.$emit("onChangePage", "Index");
            }).catch(() => this.$root.$emit("onLogout"));
        });

        this.$root.$on("onLogout", () => {
            localStorage.removeItem("jwt");
            this.user = null;
        });

        this.$root.$on("onWritePost", (title, text) => {
            if (this.user) {
                if (!title || title.length < 4) {
                    this.$root.$emit("onWritePostValidationError", "Title is too short");
                    return;
                }
                if (!text || text.length < 4) {
                    this.$root.$emit("onWritePostValidationError", "Text is too short");
                    return;
                }

              let user = localStorage.getItem("jwt");
              axios.post("/api/1/posts", {
                    title, text, user
                }).then(() => {
                    this.updatePosts();
                    this.$root.$emit("onChangePage", "Index");
                }).catch(error => {
                    this.$root.$emit("onWritePostValidationError", error.response.data);
                });
            } else {
                this.$root.$emit("onWritePostValidationError", "No access");
            }
        });
    }
}
</script>

<style>
#app {

}
</style>
