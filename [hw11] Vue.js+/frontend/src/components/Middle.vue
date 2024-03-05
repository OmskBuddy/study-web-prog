<template>
    <div class="middle">
        <Sidebar :posts="viewLastPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="viewPosts"/>
            <Users v-if="page === 'Users'" :users="viewUsers"/>
            <WritePost v-if="page === 'WritePost'"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "@/components/main/Users.vue";
import WritePost from "@/components/main/WritePost.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
      WritePost,
      Users,
        Register,
        Enter,
        Index,
        Sidebar
    },
    props: ["posts", "users"],
    computed: {
        viewLastPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        viewPosts: function () {
          return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        viewUsers: function () {
          return Object.values(this.users).sort((a, b) => b.id - a.id);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
