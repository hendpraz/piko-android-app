package com.hpdev.piko.di

import com.hpdev.piko.contacts.ContactsActivity
import com.hpdev.piko.contacts.ContactsFragment
import com.hpdev.piko.core.di.CoreComponent
import com.hpdev.piko.detail.DetailUserActivity
import com.hpdev.piko.favorites.FavoritesActivity
import com.hpdev.piko.home.HomeContactsFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeContactsFragment)
    fun inject(fragment: ContactsFragment)
    fun inject(activity: ContactsActivity)
    fun inject(activity: FavoritesActivity)
    fun inject(activity: DetailUserActivity)
}