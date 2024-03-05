<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov
        <p>Number of users: {{ countOfUsers }}</p>
        <p>Number of posts: {{ countOfPosts }}</p>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["countOfUsers", "countOfPosts"]
}
</script>

<style scoped>

</style>