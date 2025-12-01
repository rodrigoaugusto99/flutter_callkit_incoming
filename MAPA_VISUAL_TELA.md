# 📱 Mapa Visual da Tela de Chamada

## Layout da Tela (Quando Celular está Bloqueado)

```
┌─────────────────────────────────────┐
│                                     │
│         [Status Bar Area]           │
│                                     │
├─────────────────────────────────────┤
│                                     │
│                                     │
│           🔵 🔵 🔵 🔵              │ <- Animação de ondas
│         🔵           🔵            │    (llBackgroundAnimation)
│       🔵     [LOGO]     🔵         │ <- Logo (ivLogo)
│      🔵                  🔵        │
│      🔵     ┌─────┐     🔵        │
│      🔵     │     │     🔵        │ <- Avatar (ivAvatar)
│      🔵     │ 👤  │     🔵        │
│       🔵    └─────┘    🔵         │
│         🔵           🔵            │
│           🔵 🔵 🔵 🔵              │
│                                     │
│          João Silva                 │ <- Nome (tvNameCaller)
│        +55 11 98765-4321           │ <- Número (tvNumber)
│     📞 Chamada de trabalho         │ <- Mensagem personalizada
│                                     │    (tvCustomMessage) - NOVO!
│                                     │
│                                     │
│                                     │
│          [Espaço flexível]          │
│                                     │
│                                     │
│                                     │
├─────────────────────────────────────┤
│                                     │
│   ┌─────┐               ┌─────┐   │
│   │  ❌  │               │  ✅  │   │ <- Botões
│   │     │               │     │   │    (ivDeclineCall / ivAcceptCall)
│   └─────┘               └─────┘   │
│  Recusar                Aceitar    │ <- Textos
│                                     │    (tvDecline / tvAccept)
│         [Navigation Bar Area]      │
└─────────────────────────────────────┘
```

## 🎨 Componentes Personalizáveis

### 1. Background

- **ID**: `ivBackground`
- **Tipo**: ImageView
- **Personalizações**:
  - Cor sólida via `backgroundColor`
  - Imagem via `backgroundUrl`
  - Escala: centerCrop (preenche a tela)

### 2. Animação de Ondas

- **ID**: `llBackgroundAnimation`
- **Tipo**: RippleRelativeLayout
- **Função**: Efeito visual de ondas concêntricas
- **Personalizações**: Modificar no código Kotlin

### 3. Logo

- **ID**: `ivLogo`
- **Tipo**: ImageView
- **Personalizações**:
  - URL/Asset via `logo`
  - Visibilidade via `isShowLogo`
  - Posição: acima do avatar

### 4. Avatar

- **ID**: `ivAvatar`
- **Tipo**: CircleImageView (circular)
- **Personalizações**:
  - URL/Asset via `avatar`
  - Borda branca semi-transparente
  - Tamanho: definido em `@dimen/size_avatar`

### 5. Nome do Contato

- **ID**: `tvNameCaller`
- **Tipo**: TextView
- **Personalizações**:
  - Texto via `nameCaller`
  - Cor via `textColor`
  - Tamanho: auto-ajustável (12sp - 30sp)
  - Max: 1 linha com ellipsize

### 6. Número/Handle

- **ID**: `tvNumber`
- **Tipo**: TextView
- **Personalizações**:
  - Texto via `handle`
  - Cor via `textColor`
  - Visibilidade via `isShowCallID`

### 7. Mensagem Personalizada ⭐ NOVO

- **ID**: `tvCustomMessage`
- **Tipo**: TextView
- **Personalizações**:
  - Texto via `extra: {'customMessage': 'texto'}`
  - Cor: branca
  - Tamanho: 14sp
  - Visibilidade: apenas se houver texto

### 8. Botão Recusar

- **ID**: `ivDeclineCall`
- **Tipo**: ImageView
- **Personalizações**:
  - Ícone: `ic_decline`
  - Background: `bg_button_decline` (vermelho)
  - Animação: ondas concêntricas

### 9. Texto "Recusar"

- **ID**: `tvDecline`
- **Tipo**: TextView
- **Personalizações**:
  - Texto via `textDecline`
  - Cor via `textColor`

### 10. Botão Aceitar

- **ID**: `ivAcceptCall`
- **Tipo**: ImageView
- **Personalizações**:
  - Ícone: `ic_accept` (áudio) ou `ic_video` (vídeo)
  - Background: `bg_button_accept` (verde)
  - Animação: shake (tremor)

### 11. Texto "Aceitar"

- **ID**: `tvAccept`
- **Tipo**: TextView
- **Personalizações**:
  - Texto via `textAccept`
  - Cor via `textColor`

## 📏 Estrutura de Layout

```xml
RelativeLayout (raiz)
└── ImageView (ivBackground) - Cor/Imagem de fundo
    └── LinearLayout (vertical)
        ├── RippleRelativeLayout (llBackgroundAnimation) - Parte superior
        │   ├── ImageView (ivLogo)
        │   ├── CircleImageView (ivAvatar)
        │   └── LinearLayout (vertical)
        │       ├── TextView (tvNameCaller)
        │       ├── TextView (tvNumber)
        │       └── TextView (tvCustomMessage) ⭐ NOVO
        │
        ├── Space (flexível) - Empurra botões para baixo
        │
        └── LinearLayout (llAction) - Botões na parte inferior
            ├── LinearLayout (Botão Recusar)
            │   └── RippleRelativeLayout
            │       ├── ImageView (ivDeclineCall)
            │       └── TextView (tvDecline)
            │
            ├── Space (entre botões)
            │
            └── LinearLayout (Botão Aceitar)
                └── RippleRelativeLayout
                    ├── ImageView (ivAcceptCall)
                    └── TextView (tvAccept)
```

## 🎯 Áreas de Personalização

### Zona 1: Topo (Logo e Avatar)

```
Altura: ~50% da tela
Elementos: Logo, Avatar, Animação de ondas
Personalizável: ✅ Sim
```

### Zona 2: Meio (Informações)

```
Altura: Dinâmica
Elementos: Nome, Número, Mensagem personalizada
Personalizável: ✅ Sim
```

### Zona 3: Base (Ações)

```
Altura: ~100-150dp
Elementos: Botões de aceitar/recusar
Personalizável: ✅ Sim
```

## 🔧 Como Adicionar Novos Elementos

### Onde adicionar na hierarquia:

1. **Acima do Avatar**: Adicionar no `llBackgroundAnimation` antes do `ivAvatar`
2. **Abaixo do Avatar**: Adicionar no `LinearLayout` com `tvNameCaller`
3. **Novos Botões**: Adicionar no `llAction` entre os botões existentes
4. **Overlay**: Adicionar no `RelativeLayout` raiz

### Exemplo: Badge de Status

```xml
<!-- Adicionar no RippleRelativeLayout, antes do ivLogo -->
<TextView
    android:id="@+id/tvStatusBadge"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_above="@id/ivLogo"
    android:layout_centerHorizontal="true"
    android:layout_marginBottom="8dp"
    android:background="@drawable/rounded_red_background"
    android:paddingLeft="12dp"
    android:paddingTop="4dp"
    android:paddingRight="12dp"
    android:paddingBottom="4dp"
    android:text="URGENTE"
    android:textColor="@android:color/white"
    android:textSize="10sp"
    android:textStyle="bold"
    android:visibility="gone" />
```

## 🎨 Temas de Cores Sugeridos

### Profissional

- Background: `#0277BD` (Azul escuro)
- Text: `#FFFFFF` (Branco)

### Urgente

- Background: `#D32F2F` (Vermelho)
- Text: `#FFFFFF` (Branco)

### Pessoal

- Background: `#7B1FA2` (Roxo)
- Text: `#FFFFFF` (Branco)

### Escuro/Noturno

- Background: `#212121` (Cinza escuro)
- Text: `#FFFFFF` (Branco)

### Médico/Saúde

- Background: `#00897B` (Verde azulado)
- Text: `#FFFFFF` (Branco)

## 📱 Responsividade

O layout se adapta automaticamente a:

- ✅ Diferentes tamanhos de tela
- ✅ Orientação (portrait/landscape)
- ✅ Status bar e navigation bar
- ✅ Tablets (layout especial em `layout-w600dp-land/`)

## 🎭 Animações Incluídas

1. **Ondas concêntricas**: Nos botões de ação
2. **Shake (tremor)**: No botão de aceitar
3. **Ripple**: Background com efeito de ondas

## 💡 Dicas de UX

1. **Contraste**: Sempre use cores com bom contraste entre texto e fundo
2. **Legibilidade**: Mantenha textos importantes em tamanho >= 14sp
3. **Área de toque**: Botões devem ter no mínimo 48dp x 48dp
4. **Hierarquia visual**: Nome > Número > Mensagem extra
5. **Feedback visual**: Mantenha as animações para feedback tátil

## 🔍 Testando Personalizações

1. **Com tela desbloqueada**: Notificação normal
2. **Com tela bloqueada**: Tela full-screen (essa que personalizamos)
3. **Teste em diferentes cenários**:
   - Celular bloqueado
   - Celular em uso
   - Durante outra chamada
   - Com app em background
   - Com app fechado

---

**Nota**: As modificações visuais só aparecem quando o celular está **bloqueado/dormindo**.
Quando o celular está desbloqueado, aparece apenas a notificação push padrão.
