<header>
    <p style="font-size: 10px; margin: 3px 0 0 30px; padding: 0;"><%= new java.util.Date() %></p>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">

        <a class="navbar-brand" href="#">
            <i class="fab fa-accusoft"></i>
        </a>



        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link" href="">
                     Welcome ${characterName}
                </a>
            </li>
        </ul>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="arena">Arena</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="challenge">Challenges</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="fighterselection">Fight</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="shop">Shop</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/Alita_Battle_war_exploded">Alita Battle</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="community">News&Community</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="contact">Contact</a>
            </li>
        </ul>
        </div>
    </nav>
</header>