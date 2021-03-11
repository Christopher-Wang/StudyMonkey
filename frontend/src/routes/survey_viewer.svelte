<svelte:head>
    <title>Study Monkey</title>
</svelte:head>

<script>
    import natureBkg from 'images/Pine.jpg';
    import {goto} from '@sapper/app';
    import SurveyList from "../components/SurveyList.svelte";

    function handleHomeClick() {
        
    }

</script>


<div class="homeBody">
    <h1>Study Monkey!</h1>
</div>


<div class = "surveyContainer">
    <h2 id="SurveyListHeader"> List of Surveys </h2>
    <SurveyList/>
    <button on:click={() => goto('/')}>
        Home
    </button>
</div>


<style>
    .homeBody {
        text-align: center;
        padding: 1em;
        margin: 0 auto;
        background-color: #162b32;
        height: 200px;
    }

    .surveyContainer {
        margin: 60px auto;
        width: 530px;
    }


    #SurveyListHeader {
        font-weight: bold;
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
        text-align: center;
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
        margin: 0 auto;
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
