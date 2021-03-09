<svelte:head>
	<title>Survey Chimp</title>
</svelte:head>

<script>
    import natureBkg from 'images/Pine.jpg';
    import {goto} from '@sapper/app'

    function handleCreateClick() {
        goto('/survey_creator');
    }
</script>


<div class="homeBody">
    <h1>Survey Chimp!</h1>
    <p>Let's make a quick survey to get going!</p>
    <button on:click={handleCreateClick}>Create Survey</button>
</div>
<figure>
    <img alt="Nature" src="{natureBkg}">
    <figcaption>This can be some other content!</figcaption>
</figure>


<style>
    .homeBody {
        text-align: center;
        padding: 1em;
        margin: 0 auto;
        background-color: #162b32;
        height: 200px;
    }

    h1 {
        color: white;
        text-transform: uppercase;
        font-size: 4em;
        font-weight: 700;
        margin-bottom: 0px;
    }

    p {
        color: white;
        margin-top: 0px;;
    }

    button {
        background-color: #ff4838;
        color: white;
        padding: 10px;
        border-radius: 20px;
        border-color: white;
        border-width: 2px;
        font-weight: 500;
        font-size: large;
    }

    figure {
        padding-top: 20px;
        margin: 0 0 1em 0;
        text-align: center;
    }

    img {
        width: 100%;
        max-width: 500px;
        margin: 0 0 1em 0;
    }

    @media (min-width: 640px) {
        .homeBody {
            max-width: none;
        }
    }
</style>
