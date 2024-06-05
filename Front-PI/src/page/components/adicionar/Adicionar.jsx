import React from 'react';
import Modal from 'react-modal';

const Adicionar = ({ isOpen, onRequestClose, onAdicionar }) => {
  const [novoEvento, setNovoEvento] = React.useState({
    title: '',
    start: '',
    end: '',
    desc: '',
    color: '',
    tipo: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNovoEvento({ ...novoEvento, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (novoEvento.title && novoEvento.start && novoEvento.end) {
      const startDate = new Date(novoEvento.start);
      const endDate = new Date(novoEvento.end);

      if (startDate >= endDate) {
        alert('A data início deve ser anterior à data de término');
        return;
      }
      onAdicionar(novoEvento);
      setNovoEvento({
        title: '',
        start: '',
        end: '',
        desc: '',
        color: '',
        tipo: '',
      });
      onRequestClose();
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Adicionar Evento"
      ariaHideApp={false}
      style={{
        overlay: {
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          zIndex: 1000,
        },
        content: {
            top: '50%',
            left: '50%',
            right: 'auto',
            bottom: 'auto',
            marginRight: '-50%',
            transform: 'translate(-50%, -50%)',
            backgroundColor: 'white',
            padding: '20px',
            borderRadius: '10px',
            width: '80%',
            maxWidth: '500px',
            zIndex: 1001,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        },
      }}
    >
<h3 style={{ marginBottom: '20px' }}>Adicionar Evento</h3>
    <form onSubmit={handleSubmit} style={{ width: '100%', display: 'flex', flexDirection: 'column', gap: '15px' }}>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Título do Evento</label>
        <input type="text" name="title" value={novoEvento.title} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Início</label>
        <input type="datetime-local" name="start" value={novoEvento.start} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Término</label>
        <input type="datetime-local" name="end" value={novoEvento.end} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Descrição</label>
        <input type="text" name="desc" value={novoEvento.desc} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Cor</label>
        <input type="color" name="color" value={novoEvento.color} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px' }}>
        <label style={{ marginBottom: '5px' }}>Tipo</label>
        <input type="text" name="tipo" value={novoEvento.tipo} onChange={handleChange} style={{ marginTop: '5px', padding: '8px', borderRadius: '4px', border: '1px solid #ced4da' }} />
      </div>
      <div className="modal-footer" style={{ display: 'flex', justifyContent: 'flex-end', width: '100%', marginTop: '20px' }}>
        <button type="submit" style={{ marginLeft: '10px', padding: '8px 16px', border: 'none', borderRadius: '4px', backgroundColor: '#28a745', color: 'white' }}>Salvar</button>
        <button type="button" onClick={onRequestClose} style={{ marginLeft: '10px', padding: '8px 16px', border: 'none', borderRadius: '4px', backgroundColor: '#dc3545', color: 'white' }}>Cancelar</button>
      </div>
    </form>
    </Modal>
  );
};

export default Adicionar;
