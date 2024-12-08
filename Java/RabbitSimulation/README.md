### About This Project
This program was the first project I completed during my second semester in the
CS major, and was originally written in the Eclipse IDE for Java Developers.

_Last Updated: March 7th, 2022_

### Attachments
In the attachments folder, you will find a word document containing a writeup
analyzing the runtime complexity of `RabbitSimulation.java`. In it, I attempt to
explain and justify my reasoning behind my result, and also explain ways I can
(or cannot) improve the runtime complexity.

# OVERVIEW
This program runs simulations of rabbit populations given a seed file that
tracks each population. Multiple trials are run. Demographical information
(number of rabbits of each sex, total population) is tracked and displayed in
the console upon completion.

The number of times the simulation is run corresponds to the number of rows
filled in `RabbitSeeds.txt`. In each simulation, there are 10 "trials." In
each trial, the starting number of does and bucks is the same (as specified in
`RabbitSeeds.txt`), but as the rabbits are allowed to propagate, the total
numbers of does and bucks multiplies in different manners according to
pseudo-randomness afforded by the `Random` class. Since rabbit births depend on
the number of does present, drastically different end results are possible.  

## Rules of the simulation:
- A trial lasts 365 "days."
- A doe can become pregnant if:
    - They are 100+ days old (or are one of the starting does)
    - A period of 7 days has passed since their last birth
- When a doe becomes pregnant, a gestation period between 28-32 days (inclusive)
begins. After the gestation period ends, the doe gives birth and must wait 7
days to be impregnated again.
    - The newborn rabbit has a 50/50 chance of being a doe or a buck.
- After a trial ends, the demographical data (number of does, bucks, and the
total population) is saved and displayed to the user.
- After 10 trials are completed, the average number of total rabbits, does, and
bucks for the 10 trials are calculated along with their corresponding standard
deviations and are displayed.

### `RabbitSeeds.txt`'s Content
Each row in `RabbitSeeds.txt` denotes the number of starting does and bucks
(specified in that order) in each simulation.

## OUTPUT
The data for a single simulation will appear in a block of text including the
following:
- Starting does and bucks
- Demographical data (total population, number of does, number of bucks) for
each trial
- The averages of the aforementioned data over the 10 trials, along with their
corresponding standard deviations
