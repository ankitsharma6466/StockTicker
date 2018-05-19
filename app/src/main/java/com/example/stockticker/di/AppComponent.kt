import com.ankitsharma.androidkotlinboilerplate.di.modules.ActivityBuilderModule
import com.ankitsharma.androidkotlinboilerplate.di.modules.AppModule
import com.ankitsharma.androidkotlinboilerplate.di.modules.ViewModelModule
import com.example.stockticker.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main application component that connects all the dependency providers(modules) to application
 *
 * Created by ankitsharma on 19/05/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class))
interface AppComponent: AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MyApplication>()
}