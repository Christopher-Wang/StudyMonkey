<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import {goto} from '@sapper/app';
    import {surveyId} from "../stores.js";

    const url = 'http://localhost:8080/survey/createSurvey';
    const survey = {name: "", description: "", status: "CLOSED"};
    let x = JSON.stringify(survey);

    function handleRegisterSurvey() {
        const options = {method: "POST", body: JSON.stringify(survey), headers: {'Content-Type': 'application/json'}};
        let response = fetch(url, options).then(res => res.json()).then(res =>  surveyId.set(res[0].id)).then(goto("/question_creator"));
    }


</script>

<h1>Survey Creation</h1>

<form>
    <h3>Survey Name: <input placeholder="Enter a name" bind:value={survey.name}></h3>
    <h3>Survey Description: <textarea placeholder="Enter a description for your survey"
                                      bind:value={survey.description}/></h3>
    <p>{survey.name} + {survey.description}</p>
    <p>{x}</p>
    <br>

</form>
<button on:click={handleRegisterSurvey}>Register Survey</button>

<style>
    form, h1 {
        text-align: center;
    }

    textarea {
        width: 300px;
        height: 100px;
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
</style>