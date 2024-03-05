<template>
  <div>
    <PostCard v-for="post in posts" :post="post" :user="post.user" :key="post.id"/>
  </div>
</template>

<script>
import PostCard from "@/components/post/PostCard.vue";

export default {
  name: "Post",
  components: {PostCard},
  props: ["posts"]
}
</script>

<style scoped>

</style>