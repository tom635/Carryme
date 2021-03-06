package com.example.carryme

import android.app.Application
import com.example.carryme.data.db.ShoppingDatabase
import com.example.carryme.data.repositories.ShoppingRepository
import com.example.carryme.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import kotlin.math.sin

class ShoppingKodein :Application(),KodeinAware {
    override val kodein: Kodein= Kodein.lazy {
        import(androidXModule(this@ShoppingKodein))
        bind() from singleton { ShoppingDatabase(instance()) }
        bind() from singleton {  ShoppingRepository(instance()) }
        bind() from provider {
            ShoppingViewModelFactory(instance())
        }
    }


}