<svelte:head>
    <title>Question Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
</script>

<h1>Survey Creation</h1>
<h2>Create a Question for a Survey</h2>
<QuestionCreator/>



<style>
    h1, h2{
        text-align: center;
    }

    .option{
        width: 300px;
    }

    textarea {
        width: 300px;
        height: 100px;
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
</style>