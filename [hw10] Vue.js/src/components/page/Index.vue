<template>
    <Post :posts="posts" :users="users" :comments="comments"/>
</template>

<script>
import Post from "@/components/post/Post.vue";

export default {
    name: "Index",
    components: {Post},
    props: ["posts", "users", "comments"]
}
</script>

<style scoped>

</style>
