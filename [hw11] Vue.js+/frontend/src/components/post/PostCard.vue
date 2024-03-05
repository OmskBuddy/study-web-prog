<template>
  <article>
    <div class="title">
      <a href="#" style="text-decoration: none; color: inherit; white-space: pre-wrap">
        {{ post.title }}
      </a>
    </div>
    <div class="information">By {{ user.login }}</div>
    <div class="body" style="white-space: pre-wrap">{{ post.text }}</div>
    <ul class="attachment">
      <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
      <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
    </ul>
    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a href="#"></a>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  name: "PostCard",
  props: ["post", "user"],
  methods: {
    changePage: function (page) {
      this.$root.$emit("onChangePage", page);
    },
    redirectToPostPage: function () {
      this.$root.$emit("onRedirectToPostPageById", this.post.id);
    }
  }
}
</script>

<style scoped>

</style>