package com.sde.assigment.dagger.components

import com.sde.assigment.dagger.scopes.PerActivity
import com.sde.assigment.home.HomeActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {
  fun inject(activity: HomeActivity?)
}
