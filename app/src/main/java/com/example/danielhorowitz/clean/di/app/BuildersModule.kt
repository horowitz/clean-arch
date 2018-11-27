import android.app.Activity
import com.example.danielhorowitz.clean.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * This module contains all the binding to the sub component builders in the app
 */
@Module
abstract class BuildersModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindMainActivityInjectorFactory(builder: MainSubComponent.Builder): AndroidInjector.Factory<out Activity>

}