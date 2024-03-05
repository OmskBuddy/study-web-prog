<template>
    <div class="middle">
        <Sidebar :posts="viewLastPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="viewPosts" :users="users" :comments="comments"/>
            <Enter v-if="page === 'Enter'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="viewUsers"/>
            <PostPage v-if="page === 'PostPage'" :postId="postId" :posts="posts" :users="users" :comments="comments"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "./page/Register";
import Users from "./page/Users";
import PostPage from "@/components/page/PostPage.vue";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index"
        }
    },
    components: {
      PostPage,
        EditPost,
        Enter,
        Index,
        Sidebar,
        Register,
        Users,
        WritePost,
    },
    props: ["posts", "users", "comments", "postId"],
    computed: {
        viewLastPosts: function () {
          return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        },
        viewPosts: function () {
          return Object.values(this.posts).sort((a, b) => b.id - a.id);
        },
        viewUsers: function () {
          return Object.values(this.users).sort((a,b) => b.id - a.id);
        }
    },
    beforeCreate() {
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
