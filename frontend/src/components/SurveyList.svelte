<script>
    import {onMount} from 'svelte';
    import {goto} from '@sapper/app';
    import Box from "./Box.svelte";
    import {surveyId} from "../stores.js";

    const url = 'http://localhost:8080/survey/surveyList';
    let response = "";
    onMount(() => {
        fetch(url).then(res => res.json()).then(res => response = res);
    });
    function handleOnClick(id){
        surveyId.set(id);
        goto("/question_viewer")

    }

</script>

<div>
    {#each response as res}
        <div on:click={()=> handleOnClick(res.id)}>
            <Box >
                <h4> <strong>Survey Name:</strong> {res.name} </h4>
                <h4><strong>Survey Description:</strong> {res.description} </h4>
                <h4><strong>Survey Id:</strong> {res.id} </h4>
                {#if res.status == 1}
                    <h4><strong>Survey Status:</strong> CLOSED </h4>
                {:else}
                    <h4><strong>Survey Status:</strong> OPEN </h4>
                {/if}
            </Box>
        </div>
    {/each}
</div>
