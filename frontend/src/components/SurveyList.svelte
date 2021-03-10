<script>
    import {onMount} from 'svelte';
    import Box from "./Box.svelte";

    const url = 'http://localhost:8080/survey/surveyList';
    let response = "";
    onMount(() => {
        fetch(url).then(res => res.json()).then(res => response = res);
    });

</script>

<div>
    {#each response as res}
        <Box>
            <label><strong>Survey Name:</strong> {res.name} </label><br>
            <label><strong>Survey Description:</strong> {res.description} </label><br>
            <label><strong>Survey Id:</strong> {res.id} </label><br>
            {#if res.status == 1}
                <label><strong>Survey Status:</strong> CLOSED </label>
            {:else}
                <label><strong>Survey Status:</strong> OPEN </label>
            {/if}
            <br/>
        </Box>
    {/each}
</div>
