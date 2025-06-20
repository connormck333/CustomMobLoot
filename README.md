# CustomMobLoot
Check it out on Spigot with 100+ downloads: [here](https://www.spigotmc.org/resources/1-19-1-21-custommobloot.120424/)  

CustomMobLoot is a powerful and versatile plugin designed for Minecraft server owners who want complete control over mob drops and experience points. With CustomMobLoot, you can configure custom loot tables for each mob type, setting unique items, drop chances, amounts, and XP rewards based on your server's needs. This allows for endless customization possibilities, whether you're building a classic RPG experience, creating rare item drops, or simply balancing the in-game economy.

![Demo](./resources/demo.gif)

## How it works
Configure the config.yml file to add your custom drops. You must follow use the identifiers found on MinecraftWiki for both items and mobs.  
For mobs, go to <a href="https://minecraft.wiki/w/Mob#List_of_mobs">here</a>, click the mob you want, click ID and use the identifier.  
Do the same for items.  
Example config.yml:
```
mobs:
  villager:
    xp:
      minimum-drop: 10
      maximum-drop: 30
    drops:
      ender_pearl:
        chance: 50
        minimum-drop: 1
        maximum-drop: 10
      emerald:
        chance: 50
        minimum-drop: 1
        maximum-drop: 5
  warden:
    drops:
      diamond_block:
        chance: 10
        minimum-drop: 1
        maximum-drop: 5
```

## Commands
/mobloot drops custom {on/off} - Toggle all custom drops on or off  
/mobloot drops all {on/off} - Toggle all drops on or off

## Dependencies
There are no dependencies for this plugin :)  

## Versions
1.19 - 1.21  
*Tested on 1.19, 1.19.4, 1.20.1, 1.20.2, 1.20.6, 1.21.1*
