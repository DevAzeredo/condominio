package shared.design.icon

import condominio.shared.design.generated.resources.Res
import condominio.shared.design.generated.resources.ic_arrow_back
import condominio.shared.design.generated.resources.ic_backspace
import condominio.shared.design.generated.resources.ic_cancel
import condominio.shared.design.generated.resources.ic_chevron_right
import condominio.shared.design.generated.resources.ic_coffee
import condominio.shared.design.generated.resources.ic_dark_mode
import condominio.shared.design.generated.resources.ic_delete
import condominio.shared.design.generated.resources.ic_info
import condominio.shared.design.generated.resources.ic_light_mode
import condominio.shared.design.generated.resources.ic_local_drink
import condominio.shared.design.generated.resources.ic_school
import condominio.shared.design.generated.resources.ic_wine_bar

interface AppIconsProvider {

    val info: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_info)
    val cancel: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_cancel)
    val arrowBack: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_arrow_back)
    val chevronRight: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_chevron_right)
    val lightMode: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_light_mode)
    val darkMode: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_dark_mode)
    val school: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_school)
    val coffee: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_coffee)
    val wineBar: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_wine_bar)
    val localDrink: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_local_drink)
    val delete: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_delete)
    val backspace: AppIconModel
        get() = DrawableResourceModel(Res.drawable.ic_backspace)

}
