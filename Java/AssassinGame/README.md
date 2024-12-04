### About This Project
This program was originally written in the Eclipse IDE for Java Developers.

_Last Updated: April 12th, 2022_

# OVERVIEW
This program was created to demonstrate that I could construct a linked list
data structure from scratch as well as implement some methods. I achieved this
through the nested `CircLinkedStrList` class in `AssassinGame.java`. In it, I
implemented methods such as `.contains()` (linear search), `.toString()`, and
`.remove()`.

The way I chose to portray this was through representing the game of assassin, a
live-action game most popular in universities in which players seek to eliminate
other players, often with mock or faux weapons, in an effort to be the last one
standing.

This program allows the user to manage the status of the participants in a game
of assassin. All players specified in `player_names.txt` will start out alive in
the "Kill Ring." When a player is eliminated, the user can type the name of said
player to move them into the "Graveyard" or the list of eliminated players. Once
there is one player remaining, the program will automatically declare the
winner. **Please note that names are case-sensitive.**

(_For a rather amusing description of the game of assassin, check out this
resource_: https://en.wikipedia.org/wiki/Assassin_(game) )

### `player_names.txt`'s Content
`player_names.txt` may contain space, tab, or escape character-delimited names
of players that are participating in the game. When the program is initialized,
the names will be loaded appropriately.

## Output
The program will print a brief description of how to operate it to the console.
It will also print the lists of the players currently in the Kill Ring and in
the Graveyard. It will then prompt the user to input the name of a player that
has been eliminated.

After this is completed, the program will reprint the players in the Kill Ring
and Graveyard. This process repeats until there is only one player remaining, at
which point the program will announce the winner.
