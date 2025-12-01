# 🔍 Debug - Verificar Logs

## Como ver os logs no Android Studio / Logcat

1. **Abra o Logcat** no Android Studio ou use o comando:
   ```bash
   adb logcat | grep -E "DeliveryCallActivity|CallkitNotification"
   ```

2. **Limpe os logs** antes de testar:
   ```bash
   adb logcat -c
   ```

3. **Rode seu app** e chame a função

4. **Veja os logs** que aparecem

## O que procurar nos logs:

### 1. Verificar se o extra está chegando:
```
CallkitNotification: Extra in getActivityPendingIntent: {pickupAddress=..., deliveryAddress=..., estimatedTime=...}
```

### 2. Verificar qual Activity está sendo usada:
```
CallkitNotification: Has delivery data: true
CallkitNotification: Using DeliveryCallActivity
```
OU
```
CallkitNotification: Has delivery data: false
CallkitNotification: Using CallkitIncomingActivity
```

### 3. Verificar se os dados estão sendo recebidos:
```
DeliveryCallActivity: Extra data: {pickupAddress=Restaurante - Rua X, 123, ...}
DeliveryCallActivity: App name: Meu App
DeliveryCallActivity: Pickup: Restaurante - Rua X, 123
DeliveryCallActivity: Delivery: Cliente - Av. Y, 456
DeliveryCallActivity: Time: 25 minutos
DeliveryCallActivity: Accept text: PEGAR CORRIDA
DeliveryCallActivity: Decline text: NÃO QUERO
```

## Possíveis Problemas:

### ❌ Se o extra estiver NULL:
```
CallkitNotification: Extra in getActivityPendingIntent: null
```
**Solução:** O problema está na passagem de dados do Flutter para Android

### ❌ Se `hasDeliveryData` for false:
```
CallkitNotification: Has delivery data: false
```
**Solução:** Os dados não estão sendo detectados corretamente

### ❌ Se a tela antiga aparecer:
**Solução:** Está usando CallkitIncomingActivity ao invés de DeliveryCallActivity

## Testando manualmente

Execute este comando no terminal do seu projeto Flutter:

```bash
# Limpar logs
adb logcat -c

# Rodar o app em debug
flutter run

# Em outro terminal, ver os logs
adb logcat | grep -E "DeliveryCallActivity|CallkitNotification"
```

Depois teste a chamada e observe os logs.

