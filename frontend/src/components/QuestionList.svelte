<script>
    import {onMount} from 'svelte';
    import Box from "./Box.svelte";
    import {surveyId, MULTIPLE_CHOICE, TEXT} from "../stores.js";

    const url = 'https://study-monkey.herokuapp.com/survey/surveyQuestions';
    
    async function test() {
        //Temporary persistent state hack that needs to be fixed in the following sprint
        let id = document.getElementById("surveyId").value;
        const options = {method: "POST", body: JSON.stringify({"id": id}), headers: {'Content-Type': 'application/json'}};
        let res = await fetch(url, options)
        let body = await res.json()
        return body.questions
	}
    
    let promise = []

</script>

<div>
    <h3> Survey Id: <input id="surveyId" type=number bind:value={$surveyId}>
    <button on:click={() => {promise = test()}}>Click</button>
    </h3>
    {#await promise then response}
        {#each response as res}
            <Box>
                <h4><strong>Question:</strong> {res.question} </h4>
                <h4><strong>Question Id:</strong> {res.questionOrder} </h4>
                <h4><strong>Question Type:</strong> {res.questionType} </h4>   
            </Box>
        {/each}
    {/await}
</div>

<style>
    button {
        text-align: center;
        background-color: #ff4838;
        color: white;
        padding: 5px;
        border-radius: 10px;
        border-color: white;
        border-width: 2px;
        font-weight: 500;
        font-size: medium;
    }
</style>