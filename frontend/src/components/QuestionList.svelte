<script>
    import {onMount} from 'svelte';
    import Box from "./Box.svelte";
    import {surveyId, MULTIPLE_CHOICE, TEXT} from "../stores.js";

    const url = 'http://localhost:8080/survey/surveyQuestions';
    

    async function test() {
        //Temporary persistent state hack that needs to be fixed in the following sprint
        let id = document.getElementById("surveyId").value;
        const options = {method: "POST", body: JSON.stringify({"id": id}), headers: {'Content-Type': 'application/json'}};
        let res = await fetch(url, options)//.then((res) => res.json()).then((d) => alert(d)).then((data) => {return data})//.then(res => response = res);
        let body = await res.json()
        await console.log(body.questions)
        
        return body.questions
        //let res = await fetch('http://localhost:8080/survey/surveyList');
        

		// if (res.ok) {
		// 	return res;
		// } else {
		// 	throw new Error(res);
		// }
	}

    let promise = [];
    let response = []


    
</script>

<div>
    <h3> Survey Id: <input id="surveyId" type=number bind:value={$surveyId}></h3>
    <button on:click={() => {promise = test()}}>Click</button>
    {#await promise then response}
        console.log(promise)
        here
        {#each response as res}
            <Box>
                <h4><strong>Question:</strong> {res.question} </h4>
                <h4><strong>Question Id:</strong> {res.id} </h4>
                <h4><strong>Question Type:</strong> {res.questionType} </h4> 
                {#if res.questionType == TEXT}
                    <h4><strong>Answers:</strong> </h4>
                    <textarea placeholder="Write Your Answer Here"/>      
                {:else if res.questionType == MULTIPLE_CHOICE} 
                {#each res.mcOption as option, i}
                <label>
                    <input type=radio>
                    {option}
                </label>
                {/each}
                {/if}        
            </Box>
        {/each}
    {/await}
</div>
