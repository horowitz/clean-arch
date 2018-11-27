import com.dhorowitz.groupin.di.PerActivity
import com.example.danielhorowitz.clean.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * Created by danielhorowitz on 8/9/17.
 */
@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface MainSubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}