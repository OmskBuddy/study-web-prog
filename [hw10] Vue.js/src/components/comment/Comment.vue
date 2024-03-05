<template>
  <div class="comment" style="margin-top: 1rem">
    <table class="comment-table">
      <tbody><tr>
        <td class="left" style="text-align: center; width: 16rem;">
          <div class="avatar">
            <a href="#" style="position: relative;">
              <img src="@/assets/img/redruss.png" height="64" width="64">
            </a>
            <div>
              <a href="#" style="text-decoration: none; color: inherit; font-weight: bold">{{ users[comment.userId].login }}</a>
            </div>
          </div>
        </td>
        <td class="right">
          <div class="comment-content">
            <div class="comment-text" style="white-space: pre-wrap">{{ comment.text }}</div>
          </div>
        </td>
      </tr></tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "comment",
  props: ["comment", "users"]
}
</script>

<style scoped>

</style>