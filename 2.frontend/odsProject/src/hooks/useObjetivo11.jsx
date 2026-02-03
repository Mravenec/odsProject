import { useState, useEffect } from 'react';
import { objetivo11Service } from '../services/objetivo11Service';

export const useObjetivo11 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo11Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '11_1_1', '11_2_1', '11_3_1', '11_3_2', '11_4_1', '11_5_1',
      '11_5_2', '11_5_3', '11_6_1', '11_6_2', '11_7_1', '11_7_2',
      '11_a_1', '11_b_1', '11_b_2', '11_c_1'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
