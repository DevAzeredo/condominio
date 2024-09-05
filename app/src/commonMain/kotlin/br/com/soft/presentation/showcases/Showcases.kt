package br.com.soft.presentation.showcases

import br.com.soft.presentation.showcases.dataflow.encryption.BasicEncryptionShowcase
import br.com.soft.presentation.showcases.dataflow.keyvalue.`object`.ObjectKeyValueShowcase
import br.com.soft.presentation.showcases.dataflow.keyvalue.primitive.PrimitiveKeyValueShowcase
import br.com.soft.presentation.showcases.userflow.component.image.coil.CoilShowcase
import br.com.soft.presentation.showcases.userflow.component.placeholder.PlaceholderShowcase
import br.com.soft.presentation.showcases.userflow.loader.data.DataLoaderShowcase
import br.com.soft.presentation.showcases.userflow.navigation.args.ArgsNavigationShowcase
import br.com.soft.presentation.showcases.userflow.navigation.no_args.NoArgsNavigationShowcase
import br.com.soft.presentation.showcases.userflow.passcode.ResetPasscodeShowcase
import br.com.soft.presentation.showcases.userflow.passcode.SetPasscodeShowcase

/**
 * Object containing all showcase items.
 */
object Showcases {

    /**
     * A list containing all showcase items.
     */
    val all = listOf(
        ShowcaseItemGroup("Dataflow :: Encryption"),
        BasicEncryptionShowcase,
        ShowcaseItemGroup("Dataflow :: KeyValue"),
        PrimitiveKeyValueShowcase,
        ObjectKeyValueShowcase,
        ShowcaseItemGroup("Userflow :: Navigation + MVVM"),
        NoArgsNavigationShowcase,
        ArgsNavigationShowcase,
        ShowcaseItemGroup("Userflow :: Loader"),
        DataLoaderShowcase,
        ShowcaseItemGroup("Userflow :: Passcode"),
        SetPasscodeShowcase,
        ResetPasscodeShowcase,
        ShowcaseItemGroup("Userflow :: Design Components"),
        PlaceholderShowcase,
        CoilShowcase
    )

}