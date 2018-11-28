# GET

| Название  | Код |
|---------|-------------|
| [Транслитерировать казахский текст]() | 200 |
| [Получить слова исключения]() | 200 |
| [Получить символы казахской латиницы]() | 200 |

## Транслитерация казахского текста
### URL
```URL
http://translit.osmium.kz/api/text ?
& text <String>
```
### JSON [200]
```JSON
{
  "text":"content"
}
```

## Получить слова исключения
### URL
```URL
https://translit.osmium.kz/api/word
```
### JSON [200]
```JSON
[
  {
   "id":1,
   "cyrl":"содержимое",
   "latn":"content"
  }
]
```

## Получить символы казахской латиницы
### URL
```URL
https://translit.osmium.kz/api/symbol
```
### JSON [200]
```JSON
[
  {
   "id":1,
   "cyrl":"қ",
   "latn":"Q"
  }
]
```

# POST

| Название  | Код |
|---------|-------------|
| [Создание исключающего слова]() | 201 |
| [Создание символа на латинице]() | 201 |

## Создание исключающего слова
### URL
```URL
https://translit.osmium.kz/api/word
& token <String>
& cyrl  <String>
& latn  <String>
```
### JSON [201]
```JSON
{
  "id":1,
  "cyrl":"содержимое",
  "latn":"content"
}
```

## Создание символа на латинице
### URL
```URL
https://translit.osmium.kz/api/symbol
& token <String>
& cyrl  <String>
& latn  <String>
```
### JSON [201]
```JSON
{
  "id":1,
  "cyrl":"қ",
  "latn":"Q"
}
```

# PUT

| Название  | Код |
|---------|-------------|
| [Изменение исключающего слова]() | 200 |
| [Изменение символа на латинице]() | 200 |

## Изменение исключающего слова
### URL
```URL
https://translit.osmium.kz/api/word ?
    & token <String>
    & id    <Integer>
[-] & cyrl  <String>
[-] & latn  <String>
```
### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```

## Изменение символа на латинице
### URL
```URL
https://translit.osmium.kz/api/symbol ?
    & token <String>
    & id    <Integer>
[-] & cyrl  <String>
[-] & latn  <String>
```
### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```

# DELETE

| Название  | Код |
|---------|-------------|
| [Удаление исключающего слова]() | 200 |
| [Удаление символа на латинице]() | 200 |

## Удаление исключающего слова
### URL
```URL
https://translit.osmium.kz/api/word ?
& token <String>
& id    <Integer>
```
### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```

## Удаление символа на латинице
### URL
```URL
https://translit.osmium.kz/api/symbol ?
& token <String>
& id    <Integer>
```
### JSON [200]
```JSON
{
  "status":"SUCCESS"
}
```