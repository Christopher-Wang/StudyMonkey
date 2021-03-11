<svelte:head>
    <title>About</title>
</svelte:head>

<script>
    let teamName = "<b><i>Study Monkey</i></b>";
    const teamMembers = [
        "Christophe Tran",
        "Christopher Wang",
        "Michael Patsula",
        "Rahul Anilkukmar",
        "Thomas Leung",
    ];

    const linkList = [
        {
            label: "Github",
            href: "https://github.com/Christopher-Wang/StudyMonkey",
        },
        {label: "Heroku", href: " https://study-monkey.herokuapp.com/"},
        {
            label: "Figma",
            href:
                    " https://www.figma.com/file/sgJvyvbVcfeZHe15tgrBcT/Untitled?node-id=0%3A1",
        },
        {
            label: "Travis-CI",
            href:
                    "https://travis-ci.com/github/Christopher-Wang/StudyMonkey/branches",
        },
    ];
</script>

<main>
    <h1>What is Study Monkey?</h1>
    <h3>
        Study Monkey is the survey creation tool developed by team {@html teamName}.
    </h3>
    <h3>Who are we?</h3>
    <ul>
        {#each teamMembers as tm}
            <li>{tm}</li>
        {/each}
    </ul>

    <h3>Some more details!</h3>
    <p>
        SYSC4806 - Surveyor can create a survey with a list of Questions.
        Questions can be open-ended (text), asking for a number within a range,
        or asking to choose among many options. Users fill out a survey that is
        a form generated based on the type of questions in the survey. Surveyor
        can close the survey whenever they want (thus not letting in new users
        to fill out the survey), and at that point a survey result is generated,
        compiling the answers: for open-ended questions, the answers are just
        listed as-is, for number questions a histogram of the answers is
        generated, for choice questions a pie chart is generated
    </p>

    <h3>
        Important Links:

        {#each linkList as links}
            <br/>
            <a href={links.href} class="NavItem">{links.label}</a>
        {/each}
    </h3>
</main>

<style>
    main {
        text-align: center;
        padding: 1em;
        max-width: 800px;
        margin: 0 auto;
    }

    h1 {
        color: #ff6a3d;
        text-transform: uppercase;
        font-weight: 500;
    }
</style>
