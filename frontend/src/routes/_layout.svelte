<script>
	import Nav from '../components/Nav.svelte';
	import Navbar from "../components/Navbar.svelte";

	export let segment;
</script>

<style>
	/* Essentially resetting the CSS */
	:global(body) {
		margin: 0;
		padding: 0;
		width: 100%;
		height: 100%;
	}

	main {
		position: relative;
		background-color: white;
		margin: 0 auto;
		box-sizing: border-box;
	}
</style>

<Navbar/>
<main>
	<slot></slot>
</main>