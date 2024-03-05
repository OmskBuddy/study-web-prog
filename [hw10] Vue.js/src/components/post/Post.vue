<template>
  <div>
    <PostCard v-for="post in posts" :post="post" :users="users" :comments="comments" :key="post.id"/>
  </div>
</template>

<script>
import PostCard from "@/components/post/PostCard.vue";

export default {
  name: "Post",
  components: {PostCard},
  props: ["posts", "users", "comments"]
}
</script>

<style scoped>

</style>