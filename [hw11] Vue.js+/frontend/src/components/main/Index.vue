<template>
  <Post :posts="posts"/>
</template>

<script>
import Post from "@/components/post/Post.vue";

export default {
  name: "Index",
  components: {Post},
  props: ["posts"]
}
</script>

<style scoped>

</style>
