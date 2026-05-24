import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { authService } from '../../../services/authService';
import { useAuth } from '../../../hooks/useAuth.jsx';
import { ArrowLeft, Plus, Pencil, UserX, X } from 'lucide-react';
import './UsersAdminPage.css';

const ROLE_LABELS = {
  admin: 'Administrador',
  gestor: 'Profesor/Gestor',
  consultor: 'Planeación',
  evaluador: 'Evaluador',
};

const EMPTY_FORM = {
  username: '',
  email: '',
  fullName: '',
  password: '',
  rolId: '',
  sedeId: '',
};

const UsersAdminPage = () => {
  const navigate = useNavigate();
  const { getSedes } = useAuth();
  const [users, setUsers] = useState([]);
  const [roles, setRoles] = useState([]);
  const [sedes, setSedes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [modal, setModal] = useState({ open: false, mode: 'create', user: null });
  const [form, setForm] = useState(EMPTY_FORM);
  const [saving, setSaving] = useState(false);
  const [formError, setFormError] = useState('');

  useEffect(() => { load(); }, []);

  const load = async () => {
    setLoading(true);
    setError('');
    const [usersRes, rolesRes, sedesRes] = await Promise.all([
      authService.listUsers(),
      authService.getRoles(),
      getSedes(),
    ]);
    if (!usersRes.success) setError(usersRes.error);
    else setUsers(usersRes.data || []);
    if (rolesRes.success) setRoles(rolesRes.data || []);
    if (sedesRes.success) setSedes(sedesRes.data || []);
    setLoading(false);
  };

  const openCreate = () => {
    setForm(EMPTY_FORM);
    setFormError('');
    setModal({ open: true, mode: 'create', user: null });
  };

  const openEdit = (user) => {
    const rolMatch = roles.find(r =>
      (r.name || r.nombre || '').toLowerCase() === String(user.rol || '').toLowerCase()
    );
    const sedeMatch = sedes.find(s =>
      (s.nombre || '').toLowerCase() === String(user.sede || '').toLowerCase()
    );
    setForm({
      username: user.username || '',
      email: user.email || '',
      fullName: user.fullName || '',
      password: '',
      rolId: rolMatch?.id ?? '',
      sedeId: sedeMatch?.id ?? '',
    });
    setFormError('');
    setModal({ open: true, mode: 'edit', user });
  };

  const closeModal = () => {
    setModal({ open: false, mode: 'create', user: null });
    setForm(EMPTY_FORM);
    setFormError('');
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const validate = () => {
    if (!form.username.trim()) return 'El nombre de usuario es obligatorio';
    if (!form.email.trim()) return 'El correo es obligatorio';
    if (!form.fullName.trim()) return 'El nombre completo es obligatorio';
    if (!form.rolId) return 'Seleccioná un rol';
    if (!form.sedeId) return 'Seleccioná una sede';
    if (modal.mode === 'create' && !form.password) return 'La contraseña es obligatoria';
    return '';
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const v = validate();
    if (v) { setFormError(v); return; }
    setSaving(true);
    setFormError('');
    const payload = {
      username: form.username.trim(),
      email: form.email.trim(),
      fullName: form.fullName.trim(),
      password: form.password,
      rolId: parseInt(form.rolId, 10),
      sedeId: parseInt(form.sedeId, 10),
    };
    const r = modal.mode === 'create'
      ? await authService.createUser(payload)
      : await authService.updateUser(modal.user.id, payload);
    setSaving(false);
    if (!r.success) { setFormError(r.error); return; }
    closeModal();
    load();
  };

  const handleDeactivate = async (user) => {
    if (!window.confirm(`¿Desactivar al usuario "${user.username}"?`)) return;
    const r = await authService.deactivateUser(user.id);
    if (!r.success) setError(r.error);
    else load();
  };

  if (loading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Cargando usuarios...</p>
      </div>
    );
  }

  return (
    <div className="users-admin-page fade-in">
      <header className="users-admin-header">
        <div className="users-admin-header-inner">
          <div className="users-admin-title-row">
            <button type="button" className="btn-back" onClick={() => navigate('/dashboard')}>
              <ArrowLeft size={20} />
            </button>
            <div>
              <h1>Administración de usuarios</h1>
              <p>Gestión de cuentas del sistema ODS · UTN</p>
            </div>
          </div>
          <button type="button" className="btn-primary-glow" onClick={openCreate}>
            <Plus size={18} /> Nuevo usuario
          </button>
        </div>
      </header>

      <main className="users-admin-main container">
        {error && <div className="users-admin-error">{error}</div>}

        <div className="table-container">
          <table className="admin-table">
            <thead>
              <tr>
                <th>Usuario</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Sede</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {users.length === 0 ? (
                <tr>
                  <td colSpan={6} className="users-admin-empty">No hay usuarios registrados.</td>
                </tr>
              ) : users.map(u => (
                <tr key={u.id}>
                  <td data-label="Usuario">{u.username}</td>
                  <td data-label="Nombre">{u.fullName}</td>
                  <td data-label="Correo">{u.email}</td>
                  <td data-label="Rol">{ROLE_LABELS[u.rol?.toLowerCase()] || u.rol}</td>
                  <td data-label="Sede">{u.sede || '—'}</td>
                  <td data-label="Acciones">
                    <div className="users-admin-actions">
                      <button type="button" className="btn-icon" title="Editar" onClick={() => openEdit(u)}>
                        <Pencil size={16} />
                      </button>
                      <button type="button" className="btn-icon btn-icon--danger" title="Desactivar"
                        onClick={() => handleDeactivate(u)}>
                        <UserX size={16} />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </main>

      {modal.open && (
        <div className="users-modal-overlay" onClick={closeModal}>
          <div className="users-modal" onClick={e => e.stopPropagation()}>
            <div className="users-modal-header">
              <h2>{modal.mode === 'create' ? 'Nuevo usuario' : 'Editar usuario'}</h2>
              <button type="button" className="btn-icon" onClick={closeModal}><X size={18} /></button>
            </div>
            <form onSubmit={handleSave} className="users-modal-form">
              <div className="form-row">
                <label htmlFor="username">Usuario *</label>
                <input id="username" name="username" value={form.username} onChange={handleChange} required />
              </div>
              <div className="form-row">
                <label htmlFor="fullName">Nombre completo *</label>
                <input id="fullName" name="fullName" value={form.fullName} onChange={handleChange} required />
              </div>
              <div className="form-row">
                <label htmlFor="email">Correo *</label>
                <input id="email" name="email" type="email" value={form.email} onChange={handleChange} required />
              </div>
              <div className="form-row">
                <label htmlFor="password">
                  Contraseña {modal.mode === 'create' ? '*' : '(dejar vacío para no cambiar)'}
                </label>
                <input id="password" name="password" type="password" value={form.password}
                  onChange={handleChange} autoComplete="new-password" />
              </div>
              <div className="form-row">
                <label htmlFor="rolId">Rol *</label>
                <select id="rolId" name="rolId" value={form.rolId} onChange={handleChange} required>
                  <option value="">Seleccionar rol</option>
                  {roles.map(r => (
                    <option key={r.id} value={r.id}>
                      {ROLE_LABELS[(r.name || r.nombre || '').toLowerCase()] || r.name || r.nombre}
                    </option>
                  ))}
                </select>
              </div>
              <div className="form-row">
                <label htmlFor="sedeId">Sede *</label>
                <select id="sedeId" name="sedeId" value={form.sedeId} onChange={handleChange} required>
                  <option value="">Seleccionar sede</option>
                  {sedes.map(s => (
                    <option key={s.id} value={s.id}>{s.nombre}</option>
                  ))}
                </select>
              </div>
              {formError && <div className="users-form-error">{formError}</div>}
              <div className="users-modal-footer">
                <button type="button" className="btn-secondary" onClick={closeModal}>Cancelar</button>
                <button type="submit" className="btn-primary" disabled={saving}>
                  {saving ? 'Guardando...' : modal.mode === 'create' ? 'Crear' : 'Guardar'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default UsersAdminPage;
