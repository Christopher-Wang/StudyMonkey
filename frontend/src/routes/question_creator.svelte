<svelte:head>
    <title>Survey Creator</title>
</svelte:head>

<script>
    import QuestionCreator from "../components/QuestionCreator.svelte";
    import OptionCreator from "../components/OptionCreator.svelte";
    import {surveyId} from "../stores.js";


    const TEXT_URL = 'http://localhost:8080/textQuestion/addQuestion';
    const MULTIPLE_CHOICE_URL = 'http://localhost:8080/mcQuestion/addQuestion';
    const MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
    const TEXT  = "TEXT";

   
    let question = {surveyId: $surveyId, question: "", questionType: ""};
    let url = "";
    
    function toggleType(){
        if (question.questionType == MULTIPLE_CHOICE){
            question.mcOption = []
            url = MULTIPLE_CHOICE_URL;
        }
        else if (question.questionType == TEXT){
            url = TEXT_URL;
            delete question["mcOption"];
        }
    }

    function addMcOption(){
        question.mcOption[question.mcOption.length] = `Option ${question.mcOption.length + 1}`;
    }

    function submitQuestion(){
        alert(JSON.stringify(question));
        const options = {method: "POST", body: JSON.stringify(question), headers: {'Content-Type': 'application/json'}};
        let response = fetch(url, options).then(res => res.json()).then(res => surveyId.set(res[0].id));
    }

</script>

<h1>Survey Creation</h1>
<div>
    <div bind:value={question.surveyId} value={$surveyId}></div>
    <h2>Create a set of questions and answers for the survey with ID {$surveyId}</h2>
    <form>
        <h3>Enter a Question below and select a type: </h3> 
        <textarea placeholder="Write Your Quesiton Here" bind:value={question.question}/>  <br/>
        <label>
            <input type=radio bind:group={question.questionType} value={MULTIPLE_CHOICE} on:change={toggleType}>
            Multiple Choice
        </label>
        <label>
            <input type=radio bind:group={question.questionType} value={TEXT} on:change={toggleType}>
            Text
        </label>
        <br/>

        {#if 'mcOption' in question}
            {#each question.mcOption as option, i}
                <input class="option" bind:value={question.mcOption[i]} placeholder={option}> <br/>
            {/each}
        {/if}
        <br/>

        {#if question.questionType == MULTIPLE_CHOICE}
            <button type="button" on:click={addMcOption}>
                Add Option
            </button>
            {#if ("mcOption" in question) && (question.mcOption.length > 0)}
            <button on:click={submitQuestion}>
                Submit Question
            </button>
            {/if}
        {/if}

        {#if question.questionType == TEXT}
            <button on:click={submitQuestion}>
                Submit Question
            </button>
        {/if}

    </form>
    <br>
</div>
<br>


<style>
    div, h1{
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