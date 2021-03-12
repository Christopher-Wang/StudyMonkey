<script>
    import {surveyId, MULTIPLE_CHOICE, TEXT} from "../stores.js";


    const TEXT_URL = 'http://localhost:8080/textQuestion/addQuestion';
    const MULTIPLE_CHOICE_URL = 'http://localhost:8080/mcQuestion/addQuestion';

   
    let question = {surveyId: 0, question: "", questionType: ""};
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
        //Temporary persistent state hack that needs to be fixed in the following sprint
        question.surveyId = document.getElementById("surveyId").value;
        const options = {method: "POST", body: JSON.stringify(question), headers: {'Content-Type': 'application/json'}};
        let response = fetch(url, options).then(res => res.json()).then(res => surveyId.set(res[0].id));
    }

</script>


<div>
    <form>
        <h3> Survey Id: <input id="surveyId" type=number bind:value={$surveyId}></h3>
        <textarea placeholder="Write Your Question Here" bind:value={question.question}/> <br/>
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
                Submit
            </button>
            {/if}
        {/if}

        {#if question.questionType == TEXT}
            <button on:click={submitQuestion}>
                Submit
            </button>
        {/if}

    </form>
    <br>
</div>


<style>
    div{
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