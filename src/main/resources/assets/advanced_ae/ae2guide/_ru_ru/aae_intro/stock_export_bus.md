---
navigation:
  parent: aae_intro/aae_intro-index.md
  title: Шина экспорта запаса
  icon: advanced_ae:stock_export_bus_part
categories:
  - advanced items
item_ids:
  - advanced_ae:stock_export_bus_part
---

# Шина экспорта запаса

<GameScene zoom="8" background="transparent">
  <ImportStructure src="../structure/cable_stock_export_bus.snbt"></ImportStructure>
</GameScene>

Шина экспорта запаса позволяет экспортировать точное количество содержимого, заданного фильтром. Она
отслеживает количество в целевом инвентаре и не вставляет больше указанного. Для
настройки нужно открыть интерфейс, перетащить нужный предмет в слот фильтра и
средней кнопкой мыши задать количество. Шина не регулирует излишки: если предметов или
жидкости в инвентаре станет больше заданного, извлекать их она не будет.