import 'package:flutter_callkit_incoming/flutter_callkit_incoming.dart';

/// Exemplo de uso das personalizações da tela de chamada
class ExemploPersonalizacao {
  /// Exemplo 1: Chamada básica personalizada
  static Future<void> chamadaBasica() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'João Silva',
        handle: '+55 11 98765-4321',
        type: 0, // 0 = áudio, 1 = vídeo
        duration: 30000, // 30 segundos

        // Personalizações visuais
        backgroundColor: '#1E88E5',
        textColor: '#FFFFFF',
        avatar: 'https://i.pravatar.cc/300',

        // Textos personalizados
        textAccept: 'Atender',
        textDecline: 'Rejeitar',

        // Mensagem personalizada (nova funcionalidade)
        extra: <String, dynamic>{
          'customMessage': 'Chamada de trabalho',
        },
      ),
    );
  }

  /// Exemplo 2: Chamada de vídeo urgente
  static Future<void> chamadaUrgente() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-urgente-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Gerente - Maria Santos',
        handle: '+55 11 91234-5678',
        type: 1, // Vídeo
        duration: 45000, // 45 segundos

        // Visual diferenciado para chamadas urgentes
        backgroundColor: '#D32F2F', // Vermelho
        textColor: '#FFFFFF',

        // Logo da empresa
        logo: 'assets/logo_empresa.png',
        isShowLogo: true,

        // Avatar
        avatar: 'https://i.pravatar.cc/300?img=5',

        // Mostrar número
        isShowCallID: true,

        // Textos
        textAccept: 'Aceitar Vídeo',
        textDecline: 'Recusar',

        // Informações extras
        extra: <String, dynamic>{
          'customMessage': '🔴 URGENTE - Reunião de emergência',
          'isUrgent': true,
          'callType': 'video_meeting',
        },
      ),
    );
  }

  /// Exemplo 3: Chamada com imagem de fundo personalizada
  static Future<void> chamadaComBackground() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Pedro Oliveira',
        handle: 'Suporte Técnico',
        type: 0,
        duration: 30000,

        // Imagem de fundo personalizada
        backgroundUrl: 'assets/images/call_background.jpg',

        // Avatar circular
        avatar: 'https://i.pravatar.cc/300?img=12',

        // Cor do texto (branco com sombra para contraste)
        textColor: '#FFFFFF',

        // Não mostrar o handle/número
        isShowCallID: false,

        // Textos
        textAccept: 'Conectar',
        textDecline: 'Não agora',

        extra: <String, dynamic>{
          'customMessage': 'Suporte ao Cliente',
          'department': 'Técnico',
        },
      ),
    );
  }

  /// Exemplo 4: Chamada de grupo/conferência
  static Future<void> chamadaGrupo() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-grupo-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Reunião Equipe Marketing',
        handle: '5 participantes',
        type: 1, // Vídeo
        duration: 60000, // 60 segundos

        // Visual para chamadas em grupo
        backgroundColor: '#7B1FA2', // Roxo
        textColor: '#FFFFFF',

        // Logo do grupo ou empresa
        logo: 'assets/logo_grupo.png',
        isShowLogo: true,

        // Avatar genérico de grupo
        avatar: 'assets/images/group_avatar.png',

        // Mostrar informação de participantes
        isShowCallID: true,

        // Textos
        textAccept: 'Entrar',
        textDecline: 'Sair',

        extra: <String, dynamic>{
          'customMessage': '📹 Reunião Semanal',
          'callType': 'group_call',
          'participantCount': 5,
        },
      ),
    );
  }

  /// Exemplo 5: Chamada com headers para autenticação
  static Future<void> chamadaComHeaders() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Ana Costa',
        handle: '+55 11 99999-8888',
        type: 0,
        duration: 30000,

        // Avatar e background de URL privada (com autenticação)
        avatar: 'https://api.seuapp.com/avatars/ana-costa.jpg',
        backgroundUrl: 'https://api.seuapp.com/backgrounds/default.jpg',

        // Headers para autenticação
        headers: <String, dynamic>{
          'Authorization': 'Bearer seu_token_aqui',
          'X-Api-Key': 'sua_api_key',
        },

        backgroundColor: '#00897B',
        textColor: '#FFFFFF',

        textAccept: 'Aceitar',
        textDecline: 'Recusar',

        extra: <String, dynamic>{
          'customMessage': 'Chamada segura',
        },
      ),
    );
  }

  /// Exemplo 6: Chamada minimalista (sem logo, sem avatar)
  static Future<void> chamadaMinimalista() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Número Desconhecido',
        handle: '+55 11 00000-0000',
        type: 0,
        duration: 30000,

        // Cor sólida de fundo
        backgroundColor: '#424242', // Cinza escuro
        textColor: '#FFFFFF',

        // Não mostrar logo nem avatar
        isShowLogo: false,
        avatar: '', // Avatar vazio

        // Mostrar número
        isShowCallID: true,

        textAccept: 'Aceitar',
        textDecline: 'Recusar',
      ),
    );
  }

  /// Exemplo 7: Chamada com múltiplas informações personalizadas
  static Future<void> chamadaCompleta() async {
    await FlutterCallkitIncoming.showCallkitIncoming(
      CallKitParams(
        id: 'chamada-${DateTime.now().millisecondsSinceEpoch}',
        nameCaller: 'Dr. Roberto Alves',
        handle: 'Hospital Santa Maria',
        type: 1, // Vídeo
        duration: 40000,

        // Visual profissional
        backgroundColor: '#0277BD',
        textColor: '#FFFFFF',

        // Logo do hospital
        logo: 'assets/logo_hospital.png',
        isShowLogo: true,

        // Avatar do médico
        avatar: 'https://i.pravatar.cc/300?img=33',

        // Mostrar instituição
        isShowCallID: true,

        textAccept: 'Iniciar Consulta',
        textDecline: 'Cancelar',

        // Informações extras para telemedicina
        extra: <String, dynamic>{
          'customMessage': '🏥 Consulta de Telemedicina',
          'appointmentId': '12345',
          'specialty': 'Cardiologia',
          'duration': '30 minutos',
          'isUrgent': false,
        },
      ),
    );
  }

  /// Exemplo de como escutar eventos da chamada
  static void configurarListeners() {
    FlutterCallkitIncoming.onEvent.listen((CallEvent event) {
      switch (event!.event) {
        case Event.actionCallAccept:
          print('✅ Chamada aceita: ${event.body}');
          // Navegar para tela de chamada
          break;

        case Event.actionCallDecline:
          print('❌ Chamada recusada: ${event.body}');
          // Registrar chamada perdida
          break;

        case Event.actionCallEnded:
          print('📞 Chamada encerrada: ${event.body}');
          // Limpar recursos
          break;

        case Event.actionCallTimeout:
          print('⏰ Chamada expirou: ${event.body}');
          // Registrar chamada não atendida
          break;

        default:
          print('📱 Evento: ${event.event}');
      }
    });
  }
}

/// Classe auxiliar para gerenciar cores por tipo de chamada
class CallColors {
  static const String padrao = '#0955fa';
  static const String urgente = '#D32F2F';
  static const String trabalho = '#1E88E5';
  static const String pessoal = '#7B1FA2';
  static const String suporte = '#00897B';
  static const String grupo = '#6A1B9A';
  static const String medico = '#0277BD';
}

/// Exemplo de uso em um widget Flutter
/*
class TelaChamada extends StatefulWidget {
  @override
  _TelaChamadaState createState() => _TelaChamadaState();
}

class _TelaChamadaState extends State<TelaChamada> {
  @override
  void initState() {
    super.initState();
    ExemploPersonalizacao.configurarListeners();
  }
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Teste de Chamadas')),
      body: ListView(
        children: [
          ElevatedButton(
            onPressed: ExemploPersonalizacao.chamadaBasica,
            child: Text('Chamada Básica'),
          ),
          ElevatedButton(
            onPressed: ExemploPersonalizacao.chamadaUrgente,
            child: Text('Chamada Urgente'),
          ),
          ElevatedButton(
            onPressed: ExemploPersonalizacao.chamadaComBackground,
            child: Text('Chamada com Background'),
          ),
          ElevatedButton(
            onPressed: ExemploPersonalizacao.chamadaGrupo,
            child: Text('Chamada de Grupo'),
          ),
          ElevatedButton(
            onPressed: ExemploPersonalizacao.chamadaCompleta,
            child: Text('Chamada Completa'),
          ),
        ],
      ),
    );
  }
}
*/

