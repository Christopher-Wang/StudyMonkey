<svelte:head>
    <title>Study Monkey</title>
</svelte:head>

<script>
    import QuestionList from "../components/QuestionList.svelte";
</script>


<div class="homeBody">
    <h1>Study Monkey!</h1>
</div>

<div class = "surveyContainer">
    <QuestionList/>
</div>


<style>
    .homeBody {
        text-align: center;
        padding: 1em;
        margin: 0 auto;
        background-color: #162b32;
        height: 100px;
    }

    .surveyContainer {
        margin: 60px auto;
        width: 530px;
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


    @media (min-width: 640px) {
        .homeBody {
            max-width: none;
        }
    }
</style>
