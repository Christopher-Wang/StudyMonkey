<script>
    let answerType = "TEXT";

    let questions = [
        {}
    ]

    function add() {
        questions = questions.concat({done: false, text: ''});
    }

    function clear() {
        questions = questions.filter(t => !t.done);
    }
</script>



<div>
    <h2>What type of answer would you like for this question?</h2>
    <label>
        <input type=radio bind:group={answerType} value={"MC"}>
        Multiple Choice
    </label>
    <label>
        <input type=radio bind:group={answerType} value={"TEXT"}>
        Text
    </label>
</div>

<p>{answerType}</p>

<style>
    div, p{
        text-align: center;
    }
</style>

