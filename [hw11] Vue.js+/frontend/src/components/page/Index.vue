<template>
  <Post :posts="posts" :users="users"/>
</template>

<script>
import Post from "@/components/post/Post.vue";

export default {
  name: "Index",
  components: {Post},
  props: ["posts", "users"]
}
</script>

<style scoped>

</style>
