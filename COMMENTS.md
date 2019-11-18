## Comments on the assignment

- First of all, everything was written in one Activity class. So I started working on the assignment by applying **MVVM** architecture and dividing the app into layers.

- Main packages of the application are:

  - `ui`: contains activity, viewmodel, adapter, etc. classes

  - `model`: contains data model classes

  - `repository`: contains data source classes which of them work async mostly

    `di`: contains all classes which are related to dependency injection

- I created base classes to delegate some common problems to them. See `BaseActivity` and `BaseViewModel` mainly to understand what I've covered in them.

- I used **Dagger2** as dependency injection framework. Most of the code related to that resides in `di` package.

- For communication...

  - between **repository** and **viewmodel** layers, I used **Rx** **observables**.
  - between **viewmodel** and **activity/fragment** layers, I used **LiveData** objects.

- I moved all "news.json parsing & conversion into kotlin objects" logic into repository class and performed all in background thread. Additionally, I parse json string to objects using **Gson** library.

- I enabled **data binding** to reach layout items easily.

- I refactored **adapter** and **viewholder** classes benefiting from data binding.

- I applied different `LayoutManager`s to adapt application UI to different device orientations properly.

- I refactored multi hierarchical news item layout file into more flat layout using `ConstraintLayout`. This should increase the perfomance of the app.

- I followed **GitFlow** mechanism and did my development only in `develop` branch.

- I also created a `.gitignore` file in order not to commit unnecessary files.
