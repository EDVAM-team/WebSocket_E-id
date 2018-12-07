# DELETE

| Название  | Код |
|---------|-------------|
| [Удаляет сессию аккаунта]() | 200 |
| [Удаляет сессии аккаунта]() | 200 |

## Удаляет сессию аккаунта
### URL
```URL
http://account.osmium.kz/api/auth ?
& token = <String>
& id = <Integer>
```

| Аргумент  | Тип | Приоретет | Описание |
|---|---|---|---|
| `token` | String | 1 | null |
| `id` | Integer | 1 | null |

### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```

| Аргумент  | Тип | Описание |
|---|---|---|
| `status` | String | null |

## Удаляет сессии аккаунта
### URL
```URL
http://account.osmium.kz/api/auth/all ?
& token = <String>
& id = <Integer>
```

| Аргумент  | Тип | Приоретет | Описание |
|---|---|---|---|
| `token` | String | 1 | null |
| `id` | Integer | 1 | null |

### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```

| Аргумент  | Тип | Описание |
|---|---|---|
| `status` | String | null |