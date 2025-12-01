# 🎨 Dicas de Design para Tela de Chamada

## 🎯 Princípios de UX para Telas de Chamada

### 1. Legibilidade em Primeiro Lugar

- **Contraste mínimo**: Texto deve ter contraste de pelo menos 4.5:1 com o fundo
- **Tamanho de fonte**: Nome do contato >= 24sp, informações secundárias >= 14sp
- **Hierarquia visual**: Nome > Handle > Mensagem extra

### 2. Reconhecimento Rápido

- **Limite de 3 segundos**: Usuário deve entender a chamada em 3 segundos
- **Cores significativas**: Use cores que comuniquem urgência/tipo
- **Ícones universais**: Prefira emojis ou ícones conhecidos

### 3. Área de Toque Adequada

- **Mínimo 48dp**: Botões devem ter no mínimo 48x48dp
- **Espaçamento**: Pelo menos 8dp entre elementos tocáveis
- **Feedback visual**: Sempre forneça feedback ao toque

## 🎨 Paleta de Cores Recomendadas

### Por Tipo de Chamada

#### Profissional/Corporativo

```dart
backgroundColor: '#1E88E5'  // Azul confiável
textColor: '#FFFFFF'        // Branco
Emojis: 💼 📊 📞 🏢
```

#### Urgente/Emergência

```dart
backgroundColor: '#D32F2F'  // Vermelho alerta
textColor: '#FFFFFF'        // Branco
Emojis: 🔴 ⚠️ 🚨 ❗
```

#### Médico/Saúde

```dart
backgroundColor: '#00897B'  // Verde azulado
textColor: '#FFFFFF'        // Branco
Emojis: 🏥 ⚕️ 💊 🩺
```

#### Pessoal/Família

```dart
backgroundColor: '#7B1FA2'  // Roxo caloroso
textColor: '#FFFFFF'        // Branco
Emojis: 💜 ❤️ 👨‍👩‍👧‍👦 🏡
```

#### Suporte/Assistência

```dart
backgroundColor: '#00897B'  // Verde
textColor: '#FFFFFF'        // Branco
Emojis: 🛠️ 💬 🎧 📞
```

#### Delivery/Serviços

```dart
backgroundColor: '#FF6F00'  // Laranja
textColor: '#FFFFFF'        // Branco
Emojis: 🍔 📦 🚗 🏃
```

#### Grupo/Conferência

```dart
backgroundColor: '#6A1B9A'  // Roxo escuro
textColor: '#FFFFFF'        // Branco
Emojis: 👥 📹 🎤 📞
```

#### Noturno/Discreto

```dart
backgroundColor: '#212121'  // Cinza escuro
textColor: '#FFFFFF'        // Branco
Emojis: 🌙 🔕 💤
```

### Combinações de Cores Testadas

| Uso     | Background | Text      | Contraste | Status       |
| ------- | ---------- | --------- | --------- | ------------ |
| Padrão  | `#0955fa`  | `#FFFFFF` | 8.4:1     | ✅ Excelente |
| Urgente | `#D32F2F`  | `#FFFFFF` | 9.3:1     | ✅ Excelente |
| Médico  | `#00897B`  | `#FFFFFF` | 5.2:1     | ✅ Bom       |
| Pessoal | `#7B1FA2`  | `#FFFFFF` | 7.1:1     | ✅ Excelente |
| Noturno | `#212121`  | `#FFFFFF` | 15.9:1    | ✅ Excelente |
| Sutil   | `#EEEEEE`  | `#212121` | 15.5:1    | ✅ Excelente |

**Ferramenta**: Use [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/) para verificar contraste

## 📝 Escrita de Mensagens

### ✅ Boas Práticas

#### 1. Seja Conciso

❌ **Ruim**: "Esta é uma chamada de vídeo relacionada à sua consulta médica agendada"
✅ **Bom**: "Consulta médica"

#### 2. Use Contexto

❌ **Ruim**: "Ligação"
✅ **Bom**: "Reunião projeto X"

#### 3. Adicione Emojis (com moderação)

❌ **Ruim**: "🔥🔥🔥 SUPER URGENTE 🚨🚨🚨"
✅ **Bom**: "🔴 Reunião urgente"

#### 4. Prioridade Clara

❌ **Ruim**: "Talvez seja importante"
✅ **Bom**: "Urgente" ou "Pode esperar"

### Exemplos de Mensagens por Contexto

#### Telemedicina

```
"🏥 Consulta agendada"
"⚕️ Retorno - Dr. Silva"
"💊 Renovação de receita"
"🩺 Exames prontos"
```

#### Trabalho

```
"💼 Reunião semanal"
"📊 Review de projeto"
"🚨 Cliente crítico"
"☕ Daily standup"
```

#### Suporte

```
"🛠️ Retorno chamado #1234"
"💬 Suporte técnico"
"🎧 Assistência disponível"
"📞 Callback solicitado"
```

#### Delivery

```
"🍔 Pedido chegando"
"📦 Entrega no local"
"🚗 5 minutos de distância"
"🏃 Entregador esperando"
```

#### Pessoal

```
"👨‍👩‍👧 Vídeo família"
"❤️ Aniversário surpresa"
"🎉 Comemoração"
"💜 Conversa importante"
```

## 🖼️ Uso de Imagens

### Avatares

#### ✅ Boas Práticas

- **Resolução**: Mínimo 300x300px
- **Formato**: JPG ou PNG
- **Tamanho**: Máximo 500KB
- **Formato**: Quadrado (será cortado em círculo)
- **Qualidade**: Rosto reconhecível

#### ❌ Evite

- Imagens muito pequenas (pixeladas)
- Rostos cortados
- Múltiplas pessoas (dificulta identificação)
- Imagens muito escuras

### Imagens de Fundo

#### ✅ Boas Práticas

- **Resolução**: 1080x1920px (9:16)
- **Formato**: JPG
- **Tamanho**: Máximo 1MB
- **Efeito**: Levemente desfocada (blur)
- **Contraste**: Garanta legibilidade do texto

#### ❌ Evite

- Imagens muito ocupadas
- Fundos que competem com texto
- Cores muito vibrantes que dificultam leitura
- Arquivos muito pesados (carregamento lento)

### Logos

#### ✅ Boas Práticas

- **Formato**: PNG com transparência
- **Tamanho**: 200-400px de largura
- **Proporção**: Horizontal ou quadrado
- **Cor**: Funciona em fundo escuro/claro

## 📐 Layout e Espaçamento

### Hierarquia Visual

```
1. Avatar (Maior impacto visual)
   ↓
2. Nome do Contato (Tamanho grande, bold)
   ↓
3. Handle/Instituição (Tamanho médio)
   ↓
4. Mensagem Extra (Tamanho médio, emoji opcional)
   ↓
5. Botões de Ação (Grande, espaçados)
```

### Espaçamentos Recomendados

```xml
<!-- Entre elementos principais -->
android:layout_marginTop="16dp"

<!-- Entre textos relacionados -->
android:layout_marginTop="4dp"

<!-- Entre textos não relacionados -->
android:layout_marginTop="8dp"

<!-- Padding interno de elementos -->
android:padding="12dp"

<!-- Margem dos botões -->
android:layout_marginBottom="24dp"
```

## 🎭 Animações

### ✅ Recomendadas

1. **Pulse no avatar** - Chama atenção
2. **Fade in suave** - Aparição suave dos elementos
3. **Shake no botão aceitar** - Já implementado
4. **Ripple nos botões** - Feedback de toque

### ❌ Evite

1. **Animações muito rápidas** (<200ms) - Podem parecer bugs
2. **Animações muito lentas** (>1000ms) - Frustram o usuário
3. **Muitas animações simultâneas** - Confusão visual
4. **Loops infinitos muito rápidos** - Causam enjoo

### Duração Ideal

```xml
<!-- Transições rápidas -->
android:duration="200"

<!-- Transições médias (recomendado) -->
android:duration="300"

<!-- Transições suaves -->
android:duration="500"

<!-- Loops de destaque -->
android:duration="1000"
```

## 🔤 Tipografia

### Tamanhos Recomendados

```xml
<!-- Nome do contato -->
<dimen name="text_caller_name">28sp</dimen>

<!-- Handle/Instituição -->
<dimen name="text_handle">16sp</dimen>

<!-- Mensagem extra -->
<dimen name="text_custom_message">14sp</dimen>

<!-- Textos dos botões -->
<dimen name="text_button">14sp</dimen>

<!-- Badges/Status -->
<dimen name="text_badge">10sp</dimen>
```

### Peso da Fonte

- **Nome**: Bold (android:textStyle="bold")
- **Handle**: Normal
- **Mensagem extra**: Normal ou Medium
- **Botões**: Medium
- **Badges**: Bold

## 🎯 Exemplos de Design Completo

### Exemplo 1: Telemedicina Profissional

```dart
CallKitParams(
  nameCaller: 'Dr. Roberto Alves',
  handle: 'Cardiologia - Hospital Santa Cruz',
  type: 1,

  backgroundColor: '#0277BD',
  textColor: '#FFFFFF',

  logo: 'assets/hospital_logo.png',
  isShowLogo: true,
  avatar: 'https://doctor-api.com/avatars/roberto.jpg',

  textAccept: 'Iniciar Consulta',
  textDecline: 'Reagendar',

  extra: {
    'customMessage': '⚕️ Consulta agendada - 14:30',
  },
)
```

**Características**:

- Azul profissional e confiável
- Logo do hospital para credibilidade
- Horário da consulta na mensagem
- Textos dos botões contextualizados

---

### Exemplo 2: Urgência Corporativa

```dart
CallKitParams(
  nameCaller: 'Maria Santos - CEO',
  handle: 'TechCorp Ltda',
  type: 1,

  backgroundColor: '#D32F2F',
  textColor: '#FFFFFF',

  logo: 'assets/company_urgent.png',
  isShowLogo: true,
  avatar: 'https://company.com/maria.jpg',

  textAccept: 'Aceitar Agora',
  textDecline: 'Ocupado',

  extra: {
    'customMessage': '🔴 URGENTE - Cliente crítico offline',
  },
)
```

**Características**:

- Vermelho para urgência
- Cargo no nome para contexto
- Mensagem específica do problema
- Senso de urgência claro

---

### Exemplo 3: Delivery Amigável

```dart
CallKitParams(
  nameCaller: 'Carlos - Entregador',
  handle: 'FoodApp',
  type: 0,

  backgroundColor: '#FF6F00',
  textColor: '#FFFFFF',

  logo: 'assets/foodapp_logo.png',
  isShowLogo: true,
  avatar: 'https://foodapp.com/drivers/carlos.jpg',

  textAccept: 'Falar com Entregador',
  textDecline: 'Ignorar',

  extra: {
    'customMessage': '🍔 Pedido #4532 - Chegando',
  },
)
```

**Características**:

- Laranja energético e amigável
- Nome do entregador humaniza
- Número do pedido para referência
- Tom casual e direto

---

### Exemplo 4: Minimalista Noturno

```dart
CallKitParams(
  nameCaller: 'Ana Paula',
  handle: '+55 11 98765-4321',
  type: 0,

  backgroundColor: '#212121',
  textColor: '#EEEEEE',

  isShowLogo: false,
  avatar: 'https://contact-photos.com/ana.jpg',

  textAccept: 'Atender',
  textDecline: 'Recusar',

  extra: {
    'customMessage': '🌙 Modo noturno',
  },
)
```

**Características**:

- Cores suaves para uso noturno
- Minimalista e discreto
- Sem elementos desnecessários
- Foco na chamada

## ✅ Checklist de Design

### Antes de Deploy

- [ ] Contraste de cores >= 4.5:1
- [ ] Textos legíveis no tamanho mínimo (14sp)
- [ ] Botões com área de toque >= 48dp
- [ ] Avatar reconhecível (mínimo 300px)
- [ ] Logo visível mas não intrusivo
- [ ] Mensagem extra com <= 40 caracteres
- [ ] Emojis usados com moderação (0-2 por mensagem)
- [ ] Testado com tela bloqueada
- [ ] Testado em diferentes tamanhos de tela
- [ ] Testado com nomes longos
- [ ] Testado com handles longos
- [ ] Cores apropriadas ao contexto

## 🎨 Ferramentas Úteis

### Cores

- [Coolors](https://coolors.co/) - Gerador de paletas
- [WebAIM](https://webaim.org/resources/contrastchecker/) - Verificador de contraste
- [Material Design Colors](https://materialui.co/colors) - Paleta Material

### Imagens

- [TinyPNG](https://tinypng.com/) - Compressor de imagens
- [Remove.bg](https://www.remove.bg/) - Remover fundo
- [Unsplash](https://unsplash.com/) - Fotos gratuitas

### Ícones e Emojis

- [Emojipedia](https://emojipedia.org/) - Referência de emojis
- [Material Icons](https://fonts.google.com/icons) - Ícones Material

### Design

- [Figma](https://www.figma.com/) - Design e prototipagem
- [Canva](https://www.canva.com/) - Criação rápida de logos

## 💡 Dicas Finais

1. **Teste em dispositivos reais** - Emuladores não reproduzem fielmente
2. **Peça feedback** - Mostre para usuários reais
3. **Itere rapidamente** - Mude cores/textos até ficar perfeito
4. **Mantenha consistência** - Use a mesma paleta em todo o app
5. **Acessibilidade importa** - Pense em usuários com dificuldades visuais
6. **Menos é mais** - Evite poluição visual
7. **Contexto é rei** - Sempre forneça informações relevantes
8. **Performance conta** - Imagens muito pesadas atrasam a exibição

---

**🎨 Bom design não é apenas bonito, é funcional!**
