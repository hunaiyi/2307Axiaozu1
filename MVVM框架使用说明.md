# Android MVVM框架使用说明

## 框架结构

### 包结构说明
```
com.bw.a2307a1.mvvm
├── base/              # 基础类
│   ├── BaseViewModel.kt     # ViewModel基类
│   └── BaseRepository.kt    # Repository基类
├── model/             # 数据模型
│   └── User.kt              # 用户数据模型
├── network/           # 网络相关
│   ├── ApiService.kt        # 网络服务工厂
│   └── ApiResponse.kt       # API响应封装
├── repository/        # 数据仓库
│   └── UserRepository.kt    # 用户数据仓库
├── utils/             # 工具类
│   ├── Resource.kt          # 数据状态管理
│   └── Event.kt             # 事件处理
└── UserViewModel.kt   # 示例ViewModel
```

## 核心组件说明

### 1. BaseViewModel (基础ViewModel)
- 提供协程作用域管理
- 封装通用的结果处理方法
- 提供安全的协程启动方法

### 2. BaseRepository (基础Repository)
- 提供安全的API调用封装
- 统一异常处理
- 主线程切换支持

### 3. Resource (数据状态)
四种状态类型：
- `Loading`: 加载中
- `Success`: 加载成功
- `Error`: 加载失败
- `Empty`: 数据为空

### 4. Event (事件处理)
确保事件只被消费一次，避免重复处理

## 使用示例

### 创建新的ViewModel
```kotlin
class YourViewModel : BaseViewModel() {
    private val repository = YourRepository()
    
    private val _dataState = MutableLiveData<Resource<YourData>>()
    val dataState: LiveData<Resource<YourData>> = _dataState
    
    fun loadData() {
        launchOnViewModelScope {
            _dataState.value = Resource.Loading
            
            val result = repository.getData()
            
            handleResult(
                result = result,
                onSuccess = { data ->
                    _dataState.value = Resource.Success(data)
                },
                onError = { throwable ->
                    _dataState.value = Resource.Error(throwable.message ?: "加载失败")
                }
            )
        }
    }
}
```

### 在Activity中使用
```kotlin
class YourActivity : AppCompatActivity() {
    private val viewModel: YourViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel.dataState.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // 显示加载状态
                }
                is Resource.Success -> {
                    // 处理成功数据
                }
                is Resource.Error -> {
                    // 处理错误
                }
                is Resource.Empty -> {
                    // 处理空数据
                }
            }
        }
        
        // 触发数据加载
        viewModel.loadData()
    }
}
```

## 配置说明

### 网络配置
在 `ApiService.kt` 中修改：
```kotlin
private const val BASE_URL = "https://your-api-domain.com/"
```

### 添加新的API接口
1. 在 `network/` 包下创建对应的API接口
2. 在 `repository/` 包下创建对应的数据仓库
3. 创建对应的ViewModel

## 注意事项

1. **Java版本要求**: 需要Java 11或更高版本
2. **网络权限**: 记得在AndroidManifest.xml中添加网络权限
3. **API地址**: 需要替换为实际的API地址
4. **数据模型**: 根据实际API结构调整User.kt中的字段

## 依赖库说明

- `lifecycle-viewmodel-ktx`: ViewModel支持
- `lifecycle-livedata-ktx`: LiveData支持  
- `retrofit`: 网络请求
- `okhttp3`: HTTP客户端
- `kotlinx-coroutines`: 协程支持
- `navigation`: 导航组件

这个框架提供了完整的MVVM架构支持，包括数据状态管理、网络请求封装、错误处理等功能，可以直接用于实际项目开发。