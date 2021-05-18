package academy.bangkit.a2992719.favorite.di

import academy.bangkit.a2992719.core.di.DatabaseModule
import academy.bangkit.a2992719.core.di.NetworkModule
import academy.bangkit.a2992719.core.di.RepositoryModule
import academy.bangkit.a2992719.favorite.FavoriteActivity
import academy.bangkit.a2992719.moviewer.di.FavoriteModuleDependency
import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependency::class],
    modules = [
        DatabaseModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(dependency: FavoriteModuleDependency): Builder
        fun build(): FavoriteComponent
    }
}