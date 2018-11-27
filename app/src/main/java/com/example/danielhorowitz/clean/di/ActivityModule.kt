import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        internal fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)
    }
}