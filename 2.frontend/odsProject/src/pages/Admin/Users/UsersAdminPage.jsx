import React, { useState, useEffect, useMemo } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUsersAdmin } from '../../../hooks/useUsersAdmin';
import { ArrowLeft, Plus, Pencil, UserX, Eye, EyeOff, Check, Circle } from 'lucide-react';
import './UsersAdminPage.css';

const roleDisplayName = (roles, rolName) => {
  const key = String(rolName || '').toLowerCase();
  const match = roles.find(r => (r.nombre || r.name || '').toLowerCase() === key);
  return match?.nombre || match?.name || rolName || '—';
};

const EMPTY_FORM = {
  username: '',
  email: '',
  fullName: '',
  password: '',
  passwordConfirm: '',
  rolId: '',
  sedeId: '',
  areaId: '',
  dependenciaId: '',
  rolDependenciaId: '',
  unidadProgramaticaId: '',
  telefonoContacto: '',
};

const EMPTY_SODSI_CATALOGS = {
  areas: [],
  dependencias: [],
  rolesDependencia: [],
  unidades: [],
};

const PASSWORD_REQUIREMENTS = [
  { id: 'minLength', label: 'Al menos 8 caracteres', test: (p) => p.length >= 8 },
  { id: 'hasLetter', label: 'Al menos una letra', test: (p) => /[a-zA-Z]/.test(p) },
  { id: 'hasNumber', label: 'Al menos un número', test: (p) => /\d/.test(p) },
];

const getPasswordRequirements = (password) =>
  PASSWORD_REQUIREMENTS.map(({ id, label, test }) => ({
    id,
    label,
    met: test(password),
  }));

const validatePasswordFormat = (password) =>
  getPasswordRequirements(password).every((r) => r.met);

const catalogLabel = (items, id) => {
  if (id == null || id === '') return '—';
  const match = (items || []).find((x) => Number(x.id) === Number(id));
  return match?.nombre || '—';
};

const isGestorRole = (roles, rolId) => {
  const rol = roles.find((r) => String(r.id) === String(rolId));
  const name = (rol?.name || rol?.nombre || '').toLowerCase();
  return name === 'gestor';
};

const isGestorProfileIncomplete = (user, roles) => {
  const rol = String(user.rol || '').toLowerCase();
  if (rol !== 'gestor') return false;
  return !user.areaId || !user.dependenciaId || !user.rolDependenciaId || !user.sedeId;
};

const UsersAdminPage = () => {
  const navigate = useNavigate();
  const {
    users,
    roles,
    sedes,
    sodsiCatalogs,
    loading,
    error,
    saving,
    setError,
    createUser,
    updateUser,
    deactivateUser,
  } = useUsersAdmin();
  const [modal, setModal] = useState({ open: false, mode: 'create', user: null });
  const [form, setForm] = useState(EMPTY_FORM);
  const [formError, setFormError] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [showPasswordConfirm, setShowPasswordConfirm] = useState(false);

  const resetPasswordVisibility = () => {
    setShowPassword(false);
    setShowPasswordConfirm(false);
  };

  const openCreate = () => {
    setForm(EMPTY_FORM);
    setFormError('');
    resetPasswordVisibility();
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
      passwordConfirm: '',
      rolId: rolMatch?.id ?? '',
      sedeId: sedeMatch?.id ?? '',
      areaId: user.areaId != null ? String(user.areaId) : '',
      dependenciaId: user.dependenciaId != null ? String(user.dependenciaId) : '',
      rolDependenciaId: user.rolDependenciaId != null ? String(user.rolDependenciaId) : '',
      unidadProgramaticaId: user.unidadProgramaticaId != null ? String(user.unidadProgramaticaId) : '',
      telefonoContacto: user.telefonoContacto || '',
    });
    setFormError('');
    resetPasswordVisibility();
    setModal({ open: true, mode: 'edit', user });
  };

  const closeModal = () => {
    setModal({ open: false, mode: 'create', user: null });
    setForm(EMPTY_FORM);
    setFormError('');
    resetPasswordVisibility();
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
    if (formError && (name === 'password' || name === 'passwordConfirm')) {
      setFormError('');
    }
  };

  const passwordLiveFeedback = useMemo(() => {
    const pwd = form.password;
    const confirm = form.passwordConfirm;
    const changing = modal.mode === 'create' || Boolean(pwd || confirm);

    if (!changing) {
      return {
        passwordError: '',
        confirmError: '',
        confirmOk: false,
        hasLiveError: false,
        requirements: getPasswordRequirements(''),
        showRequirements: false,
        passwordFormatOk: false,
      };
    }

    const requirements = getPasswordRequirements(pwd);
    const passwordFormatOk = Boolean(pwd) && requirements.every((r) => r.met);
    const passwordError = pwd && !passwordFormatOk
      ? 'La contraseña no cumple todos los requisitos'
      : '';
    const showRequirements = modal.mode === 'create' || Boolean(pwd);

    let confirmError = '';
    let confirmOk = false;

    if (confirm) {
      if (pwd !== confirm) {
        confirmError = 'Las contraseñas no coinciden';
      } else if (validatePasswordFormat(pwd)) {
        confirmOk = true;
      }
    } else if (pwd && modal.mode === 'edit') {
      confirmError = 'Confirmá la contraseña';
    }

    return {
      passwordError,
      confirmError,
      confirmOk,
      hasLiveError: Boolean(passwordError || confirmError),
      requirements,
      showRequirements,
      passwordFormatOk,
    };
  }, [form.password, form.passwordConfirm, modal.mode]);

  const validatePasswordFields = () => {
    const pwd = form.password;
    const confirm = form.passwordConfirm;
    const changing = modal.mode === 'create' || Boolean(pwd || confirm);

    if (!changing) return '';

    if (modal.mode === 'edit' && !pwd && confirm) {
      return 'Ingresá la nueva contraseña';
    }
    if (modal.mode === 'create' && !pwd) {
      return 'La contraseña es obligatoria';
    }
    if (!confirm) {
      return 'Confirmá la contraseña';
    }
    if (pwd !== confirm) {
      return 'Las contraseñas no coinciden';
    }
    if (!validatePasswordFormat(pwd)) {
      return 'La contraseña debe tener al menos 8 caracteres, una letra y un número';
    }
    return '';
  };

  const validate = () => {
    if (!form.username.trim()) return 'El nombre de usuario es obligatorio';
    if (!form.email.trim()) return 'El correo es obligatorio';
    if (!form.fullName.trim()) return 'El nombre completo es obligatorio';
    if (!form.rolId) return 'Seleccioná un rol';
    if (!form.sedeId) return 'Seleccioná una sede';
    if (isGestorRole(roles, form.rolId)) {
      if (!form.areaId) return 'Para gestores, el área (fuente de información) es obligatoria';
      if (!form.dependenciaId) return 'Para gestores, la dependencia es obligatoria';
      if (!form.rolDependenciaId) return 'Para gestores, el rol de dependencia es obligatorio';
    }
    return validatePasswordFields();
  };

  const handleSave = async (e) => {
    e.preventDefault();
    const v = validate();
    if (v) { setFormError(v); return; }
    setFormError('');
    const payload = {
      username: form.username.trim(),
      email: form.email.trim(),
      fullName: form.fullName.trim(),
      rolId: parseInt(form.rolId, 10),
      sedeId: parseInt(form.sedeId, 10),
      areaId: form.areaId ? parseInt(form.areaId, 10) : null,
      dependenciaId: form.dependenciaId ? parseInt(form.dependenciaId, 10) : null,
      rolDependenciaId: form.rolDependenciaId ? parseInt(form.rolDependenciaId, 10) : null,
      unidadProgramaticaId: form.unidadProgramaticaId ? parseInt(form.unidadProgramaticaId, 10) : null,
      telefonoContacto: form.telefonoContacto.trim() || null,
    };
    if (form.password) {
      payload.password = form.password;
    }
    const r = modal.mode === 'create'
      ? await createUser(payload)
      : await updateUser(modal.user.id, payload);
    if (!r.success) { setFormError(r.error); return; }
    closeModal();
  };

  const handleDeactivate = async (user) => {
    if (!window.confirm(`¿Desactivar al usuario "${user.username}"?`)) return;
    const r = await deactivateUser(user.id);
    if (!r.success) setError(r.error);
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
          <div className="users-admin-header-actions">
            <button type="button" className="btn-secondary-outline" onClick={() => navigate('/admin/bitacora')}>
              Bitácora de ingresos
            </button>
            <button type="button" className="btn-secondary-outline" onClick={() => navigate('/admin/sodsi-beneficiarios')}>
              Catálogo beneficiarios SODSI
            </button>
            <button type="button" className="btn-primary-glow" onClick={openCreate}>
              <Plus size={18} /> Nuevo usuario
            </button>
          </div>
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
                <th>Unidad encargada</th>
                <th>Área</th>
                <th>Dependencia</th>
                <th>Rol dep.</th>
                <th>Teléfono</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {users.length === 0 ? (
                <tr>
                  <td colSpan={11} className="users-admin-empty">No hay usuarios registrados.</td>
                </tr>
              ) : users.map(u => (
                <tr key={u.id} className={isGestorProfileIncomplete(u, roles) ? 'users-row-incomplete' : ''}>
                  <td data-label="Usuario">
                    {u.username}
                    {isGestorProfileIncomplete(u, roles) && (
                      <span className="users-badge-incomplete" title="Perfil SODSI incompleto para export">
                        Perfil incompleto
                      </span>
                    )}
                  </td>
                  <td data-label="Nombre">{u.fullName}</td>
                  <td data-label="Correo">{u.email}</td>
                  <td data-label="Rol">{roleDisplayName(roles, u.rol)}</td>
                  <td data-label="Sede">{u.sede || '—'}</td>
                  <td data-label="Unidad encargada">{catalogLabel(sodsiCatalogs.unidades, u.unidadProgramaticaId)}</td>
                  <td data-label="Área">{catalogLabel(sodsiCatalogs.areas, u.areaId)}</td>
                  <td data-label="Dependencia">{catalogLabel(sodsiCatalogs.dependencias, u.dependenciaId)}</td>
                  <td data-label="Rol dep.">{catalogLabel(sodsiCatalogs.rolesDependencia, u.rolDependenciaId)}</td>
                  <td data-label="Teléfono">{u.telefonoContacto || '—'}</td>
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
        <div className="users-modal-overlay" role="dialog" aria-modal="true"
          aria-labelledby="users-modal-title">
          <div className="users-modal">
            <div className="users-modal-header">
              <h2 id="users-modal-title">{modal.mode === 'create' ? 'Nuevo usuario' : 'Editar usuario'}</h2>
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
                  Contraseña {modal.mode === 'create' ? '*' : '(opcional)'}
                </label>
                <div className="password-input-wrap">
                  <input
                    id="password"
                    name="password"
                    type={showPassword ? 'text' : 'password'}
                    value={form.password}
                    onChange={handleChange}
                    autoComplete="new-password"
                    required={modal.mode === 'create'}
                    aria-invalid={Boolean(passwordLiveFeedback.passwordError)}
                    aria-describedby="password-hint"
                    className={
                      passwordLiveFeedback.passwordError
                        ? 'input--error'
                        : passwordLiveFeedback.passwordFormatOk
                          ? 'input--success'
                          : ''
                    }
                  />
                  <button
                    type="button"
                    className="password-toggle"
                    onClick={() => setShowPassword(v => !v)}
                    aria-label={showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'}
                    aria-pressed={showPassword}
                    aria-controls="password"
                  >
                    {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                  </button>
                </div>
                {passwordLiveFeedback.showRequirements && (
                  <ul id="password-hint" className="password-requirements" aria-live="polite">
                    {passwordLiveFeedback.requirements.map(({ id, label, met }) => (
                      <li
                        key={id}
                        className={`password-requirement ${met ? 'password-requirement--met' : 'password-requirement--pending'}`}
                      >
                        {met ? <Check size={14} aria-hidden="true" /> : <Circle size={14} aria-hidden="true" />}
                        <span>{label}</span>
                      </li>
                    ))}
                  </ul>
                )}
              </div>
              <div className="form-row">
                <label htmlFor="passwordConfirm">
                  Repetir contraseña {modal.mode === 'create' || form.password ? '*' : '(opcional)'}
                </label>
                <div className="password-input-wrap">
                  <input
                    id="passwordConfirm"
                    name="passwordConfirm"
                    type={showPasswordConfirm ? 'text' : 'password'}
                    value={form.passwordConfirm}
                    onChange={handleChange}
                    autoComplete="new-password"
                    required={modal.mode === 'create' || Boolean(form.password)}
                    aria-invalid={Boolean(passwordLiveFeedback.confirmError)}
                    aria-describedby="passwordConfirm-hint passwordConfirm-live-feedback"
                    className={
                      passwordLiveFeedback.confirmError
                        ? 'input--error'
                        : passwordLiveFeedback.confirmOk
                          ? 'input--success'
                          : ''
                    }
                  />
                  <button
                    type="button"
                    className="password-toggle"
                    onClick={() => setShowPasswordConfirm(v => !v)}
                    aria-label={showPasswordConfirm ? 'Ocultar confirmación' : 'Mostrar confirmación'}
                    aria-pressed={showPasswordConfirm}
                    aria-controls="passwordConfirm"
                  >
                    {showPasswordConfirm ? <EyeOff size={18} /> : <Eye size={18} />}
                  </button>
                </div>
                {passwordLiveFeedback.confirmError && (
                  <span id="passwordConfirm-live-feedback" className="form-field-error" role="alert">
                    {passwordLiveFeedback.confirmError}
                  </span>
                )}
                {passwordLiveFeedback.confirmOk && (
                  <span id="passwordConfirm-live-feedback" className="form-field-success" role="status">
                    Las contraseñas coinciden
                  </span>
                )}
                {modal.mode === 'edit' && !form.password && !passwordLiveFeedback.confirmError && (
                  <span id="passwordConfirm-hint" className="form-hint">
                    Dejá ambos campos vacíos para no cambiar la contraseña.
                  </span>
                )}
              </div>
              <div className="form-row">
                <label htmlFor="rolId">Rol *</label>
                <select id="rolId" name="rolId" value={form.rolId} onChange={handleChange} required>
                  <option value="">Seleccionar rol</option>
                  {roles.map(r => (
                    <option key={r.id} value={r.id} title={r.descripcion || undefined}>
                      {r.nombre || r.name}
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

              <div className="users-sodsi-section">
                <h3>Perfil SODSI (export consultor)</h3>
                <p className="form-hint">
                  Unidad encargada, fuente de información, dependencia, rol y teléfono se incluyen en la matriz Excel al descargar.
                </p>
                <div className="form-row">
                  <label htmlFor="unidadProgramaticaId">Unidad encargada</label>
                  <select
                    id="unidadProgramaticaId"
                    name="unidadProgramaticaId"
                    value={form.unidadProgramaticaId}
                    onChange={handleChange}
                  >
                    <option value="">Seleccionar unidad</option>
                    {(sodsiCatalogs.unidades || []).map(u => (
                      <option key={u.id} value={u.id}>
                        {u.codigo ? `[${u.codigo}] ${u.nombre}` : u.nombre}
                      </option>
                    ))}
                  </select>
                </div>
                <div className="form-row">
                  <label htmlFor="telefonoContacto">Teléfono de contacto</label>
                  <input
                    id="telefonoContacto"
                    name="telefonoContacto"
                    type="tel"
                    value={form.telefonoContacto}
                    onChange={handleChange}
                    placeholder="2222-3333"
                  />
                </div>
                <div className="form-row">
                  <label htmlFor="areaId">Fuente de información (área)</label>
                  <select id="areaId" name="areaId" value={form.areaId} onChange={handleChange}>
                    <option value="">Seleccionar área</option>
                    {sodsiCatalogs.areas.map(a => (
                      <option key={a.id} value={a.id}>{a.nombre}</option>
                    ))}
                  </select>
                </div>
                <div className="form-row">
                  <label htmlFor="dependenciaId">Dependencia</label>
                  <select id="dependenciaId" name="dependenciaId" value={form.dependenciaId} onChange={handleChange}>
                    <option value="">Seleccionar dependencia</option>
                    {sodsiCatalogs.dependencias.map(d => (
                      <option key={d.id} value={d.id}>{d.nombre}</option>
                    ))}
                  </select>
                </div>
                <div className="form-row">
                  <label htmlFor="rolDependenciaId">Rol de dependencia</label>
                  <select id="rolDependenciaId" name="rolDependenciaId" value={form.rolDependenciaId} onChange={handleChange}>
                    <option value="">Seleccionar rol</option>
                    {sodsiCatalogs.rolesDependencia.map(r => (
                      <option key={r.id} value={r.id}>{r.nombre}</option>
                    ))}
                  </select>
                </div>
              </div>

              {formError && <div className="users-form-error">{formError}</div>}
              <div className="users-modal-footer">
                <button type="button" className="btn-secondary" onClick={closeModal}>Cancelar</button>
                <button
                  type="submit"
                  className="btn-primary"
                  disabled={saving || passwordLiveFeedback.hasLiveError}
                >
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
