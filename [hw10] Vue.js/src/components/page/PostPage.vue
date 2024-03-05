<template>
  <div>
    <PostCard :post="posts[postId]" :users="users" :comments="comments"/>
    <h2>Comments ({{ viewComments.length }})</h2>
    <Comment v-for="comment in viewComments" :comment="comment" :users="users" :key="comment.id"/>
  </div>
</template>

<script>
import PostCard from "@/components/post/PostCard.vue";
import Comment from "@/components/comment/Comment.vue";

export default {
  name: "PostPage",
  components: {Comment, PostCard},
  props: ["postId", "posts", "users", "comments"],
  computed: {
    viewComments: function () {
      return Object.values(this.comments).filter(cmt => cmt.postId === this.postId);
    }
  }
}
</script>

<style scoped>

</style>