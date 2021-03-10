<script>
    const navBarItems = [
        {
            label: "Sign Up",
            href: "#",
        },
        {
            label: "Login",
            href: "#",
        },
        {
            label: "About",
            href: "/about",
        },
        {
            label: "Home",
            href: "https://github.com/Christopher-Wang/StudyMonkey",
        },
    ];
</script>

<nav class="navbar">
    <div class="navVals">
        <ul class="navbarList">
            <li class="logoItem">
                <a href="/" class="Logo" alt="Survey Chimp">Survey Chimp</a>
            </li>
            {#each navBarItems as navItem}
                <li class="menuItems">
                    <a href={navItem.href} class="NavItem">{navItem.label}</a>
                </li>
            {/each}
        </ul>
    </div>
</nav>

<style>
    nav {
        background-color: #162b32;
        font-family: "Helvetica", "Ariel", sans-serif;
    }

    .Logo {
        color: white;
        padding: 10px 20px;
        text-align: center;
        display: block;
        text-decoration: none;
        padding-bottom: 15px;
        padding-top: 15px;
    }

    .navbarList {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;

    }

    .menuItems {
        float: right;
    }

    .logoItem {
        float: left;
    }

    .NavItem {
        color: white;
        padding: 10px 20px;
        text-align: center;
        display: block;
        text-decoration: none;
        padding-bottom: 15px;
        padding-top: 15px;
    }

    .NavItem:hover {
        background-color: #ff4838;
    }
    .logoItem:hover {
        background-color: #ff4838;
    }
</style>
