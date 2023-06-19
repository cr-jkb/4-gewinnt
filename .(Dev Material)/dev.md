Description | Badge
--------|--------
Git-Hub Actions | [![Scala CI](https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml/badge.svg)](https://github.com/cr-jkb/4-gewinnt/actions/workflows/scala.yml)
CoverAlls.io (full) | [![Coverage Status](https://coveralls.io/repos/github/cr-jkb/4-gewinnt/badge.svg)](https://coveralls.io/github/cr-jkb/4-gewinnt)
Prod Branch (excluded) | [![codecov](https://codecov.io/gh/cr-jkb/4-gewinnt/branch/production-(beta)/graph/badge.svg?token=B5A1IMUD2N)](https://codecov.io/gh/cr-jkb/4-gewinnt)
Dev Branch (excluded) | [![codecov](https://codecov.io/gh/cr-jkb/4-gewinnt/branch/dev-(alpha)/graph/badge.svg?token=B5A1IMUD2N)](https://codecov.io/gh/cr-jkb/4-gewinnt)
Excluded | Main.scala GUI.scala


#### Comment Notations relate to Java Definitions:
- 'Interfaces' for Traits (Interfaces are used as templates for standarized methods and variables), 'Implemented Interfaces' if there are full working methods inside, Classes, ... 
- You will not find any Object Notations as everything is an object in Scala. Instead you may find '1-Instance Classes' as an alias for case classes. 1-Instance refers to the immutability of those. 
- 'Wrapper Classes' can be also called "State-Holders" or "State-Save-Objects" and by that function like Placeholders that may be used as "Swap-In Classes/Objects". 


#### Notation for good style (agility compatible):
- do not use var, try, null, void (monads instead)


#### LAYER Order when placing a Stone in /view: (descending = from above)
- View
!
v
- Controller (savegame state within val field)
- Field
- ModeStrategy
- Player
- Stone X or O (placed using field.copy)
