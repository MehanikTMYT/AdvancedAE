---
navigation:
  parent: aae_intro/aae_intro-index.md
  title: МЭ монитор производительности
  icon: advanced_ae:throughput_monitor
categories:
  - advanced items
item_ids:
  - advanced_ae:throughput_monitor
  - advanced_ae:throughput_monitor_configurator
---

# МЭ монитор производительности

<GameScene zoom="8" background="transparent">
<ImportStructure src="../structure/throughput_monitors.snbt"></ImportStructure>
<IsometricCamera yaw="195" pitch="30" />
</GameScene>

МЭ монитор производительности — разновидность монитора. Он обладает возможностями <ItemLink id="ae2:storage_monitor" />
и дополнительно измеряет пропускную способность. Монитор отслеживает изменение количества одного
типа предметов или жидкости и показывает изменение за секунду.

Канал *не* требуется.

## Управление

*   ПКМ с предметом или двойное нажатие ПКМ с ёмкостью жидкости задаёт отслеживаемый предмет или жидкость.
*   ПКМ пустой рукой очищает монитор.
*   Shift-ПКМ пустой рукой блокирует монитор.

## Конфигуратор монитора производительности

<ItemImage id="advanced_ae:throughput_monitor_configurator" scale="4"></ItemImage>

Конфигуратор монитора производительности — инструмент для изменения отображаемых данных. Нажатие
ПКМ по монитору с ним в руке переключает три варианта:

* Предметы за тик
* Предметы за секунду
* Предметы за минуту

Примечание: после смены режима показания стабилизируются не сразу, поэтому начальным значениям доверять не стоит!