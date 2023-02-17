# feature_excel module

In this folder, we have 3 layers

- data
- domain
- presentation ( UI layer )

That means it's just a simple [android architeture](https://developer.android.com/topic/architecture/intro), but modularized

```
.
└── feature_excel/
    ├── data/
    │   ├── di/
    │   │   └── ShoeModule.kt
    │   ├── local/
    │   │   ├── database_source/
    │   │   │   ├── entity/
    │   │   │   │   └── ShoeEntity.kt
    │   │   │   ├── ShoeDAO.kt
    │   │   │   └── ShoeDatabase.kt
    │   │   └── file_source/
    │   │       ├── ShoeExcelData.kt
    │   │       ├── ShoeExcelDataManipulation.kt
    │   │       ├── ShoeExcelFile.kt
    │   │       └── ShoeExcelInfos.kt
    │   └── ShoeRepositoryImpl.kt
    ├── domain/
    │   ├── model/
    │   │   └── Shoe.kt
    │   ├── Repository/
    │   │   └── ShoeRepository.kt
    │   └── use_case/
    │       └── GetShoes.kt
    └── presentation/
        ├── ShoeState.kt
        └── ShoeViewModel.kt
```